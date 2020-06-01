package com.istudy.Access;

import com.alibaba.fastjson.JSON;
import com.istudy.pojo.MiaoshaUser;
import com.istudy.redis.AccessKey;
import com.istudy.redis.MiaoshaUserKey;
import com.istudy.result.CodeMsg;
import com.istudy.service.MiaoshaUserService;
import com.istudy.service.UserService;
import com.istudy.utils.RedisOperator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;

@Service
public class AccessInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    MiaoshaUserService miaoshaUserService;

    @Autowired
    UserService userService;

    @Autowired
    RedisOperator redisOperator;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        if(handler instanceof HandlerMethod){
            MiaoshaUser user = getUser(request, response);
            UserContext.setUser(user);
            HandlerMethod hm = (HandlerMethod)handler;
            AccessLimit accessLimit = hm.getMethodAnnotation(AccessLimit.class);

            if(accessLimit == null){
                return true;
            }
            int seconds = accessLimit.seconds();
            int maxCount =accessLimit.maxCount();
            boolean needLogin = accessLimit.needLogin();
            String key = request.getRequestURI();
            if(needLogin){
                if (user== null){
                    render(response, CodeMsg.BIND_ERROR);
                    return false;
                }
                key += "-" +user.getId();
            } else {

            }
            AccessKey accessKey = AccessKey.withExpire(seconds);
            String count = redisOperator.get(accessKey.getPrefix()+key);
            if (count == null){
                redisOperator.set(accessKey.getPrefix()+key,"1",accessKey.expireSeconds());
            } else if(Integer.parseInt(count) < maxCount){
                redisOperator.incr(accessKey.getPrefix()+key,1);
            } else{
                render(response, CodeMsg.ACCESS_LIMIT_REACHED);
                return false;
            }
        }

        return true;
    }

    private void render(HttpServletResponse response, CodeMsg cm) throws  Exception{
        response.setContentType("application/json;charset=UTF-8");
        OutputStream out = response.getOutputStream();
        String str = JSON.toJSONString(cm);
        out.write(str.getBytes());
        out.flush();
        out.close();

    }


    private MiaoshaUser getUser(HttpServletRequest request, HttpServletResponse response) {
        String paramToken = request.getParameter(MiaoshaUserService.COOKI_NAME_TOKEN);
        String cookieToken = getCookieValue(request, MiaoshaUserService.COOKI_NAME_TOKEN);
        if(StringUtils.isEmpty(cookieToken) && StringUtils.isEmpty(paramToken)) {
            return null;
        }
        String token = StringUtils.isEmpty(paramToken)?cookieToken:paramToken;
        return getByToken(response, token);
    }

    public MiaoshaUser getByToken(HttpServletResponse response, String token) {
        if(StringUtils.isEmpty(token)) {
            return null;
        }
        String value = redisOperator.get(MiaoshaUserKey.token.getPrefix() + ":" + token);
        MiaoshaUser user = JSON.toJavaObject(JSON.parseObject(value), MiaoshaUser.class);
        //延长有效期
        if(user != null) {
            addCookie(response, token, user);
        }
        return user;
    }

    private String getCookieValue(HttpServletRequest request, String cookiName) {
        Cookie[]  cookies = request.getCookies();
        if(cookies == null || cookies.length <= 0){
            return null;
        }
        for(Cookie cookie : cookies) {
            if(cookie.getName().equals(cookiName)) {
                return cookie.getValue();
            }
        }
        return null;
    }

    public void addCookie(HttpServletResponse response, String token, MiaoshaUser user) {
        redisOperator.set(MiaoshaUserKey.token.getPrefix()+":"+token,JSON.toJSONString(user));
        Cookie cooKie = new Cookie(MiaoshaUserService.COOKI_NAME_TOKEN,token);
        cooKie.setMaxAge(MiaoshaUserService.TOKEN_EXPIRE);
        cooKie.setPath("/");
        response.addCookie(cooKie);

    }
}
