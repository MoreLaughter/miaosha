package com.istudy.config;

import com.alibaba.fastjson.JSON;
import com.istudy.pojo.MiaoshaUser;
import com.istudy.redis.MiaoshaUserKey;
import com.istudy.service.MiaoshaUserService;
import com.istudy.service.impl.MiaoshaUserServiceImpl;
import com.istudy.utils.RedisOperator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class UserArgumentResolver implements HandlerMethodArgumentResolver {
    @Autowired
    MiaoshaUserService miaoshaUserService;

    @Autowired
    RedisOperator redisOperator;

  @Override
    public boolean supportsParameter(MethodParameter parameter) {
        Class<?> clazz = parameter.getParameterType();
        return clazz==MiaoshaUser.class;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
        HttpServletResponse response = webRequest.getNativeResponse(HttpServletResponse.class);

        String paramToken = request.getParameter(miaoshaUserService.COOKI_NAME_TOKEN);
        String cookieToken = getCookieValue(request, miaoshaUserService.COOKI_NAME_TOKEN);
        if(StringUtils.isEmpty(cookieToken) && StringUtils.isEmpty(paramToken)) {
            return null;
        }
        String token = StringUtils.isEmpty(paramToken)?cookieToken:paramToken;
        return getByToken(response, token);
    }

    private String getCookieValue(HttpServletRequest request, String cookiName) {
        Cookie[]  cookies = request.getCookies();
        if( cookies == null || cookies.length <= 0){
            return null;
        }
        for(Cookie cookie : cookies) {
            if(cookie.getName().equals(cookiName)) {
                return cookie.getValue();
            }
        }
        return null;
    }

    public MiaoshaUser getByToken(HttpServletResponse response, String token) {
        if(StringUtils.isEmpty(token)) {
            return null;
        }
        String value = redisOperator.get(MiaoshaUserKey.token.getPrefix() + ":" + token);
        MiaoshaUser user = JSON.toJavaObject(JSON.parseObject(value), MiaoshaUser.class);
         //JSON.toJavaObject(redisOperator.get(MiaoshaUserKey.token.getPrefix() + ":" + token),MiaoshaUser.class);
         //JSON.toJavaObject(JSON.parseObject(redisOperator.get(Miao)),MiaoshaUser.class);
        //延长有效期
        if(user != null) {
            addCookie(response, token, user);
        }
        return user;
    }

    public void addCookie(HttpServletResponse response, String token, MiaoshaUser user) {
        redisOperator.set(MiaoshaUserKey.token.getPrefix()+":"+token,JSON.toJSONString(user));
        Cookie cooKie = new Cookie(MiaoshaUserService.COOKI_NAME_TOKEN,token);
        cooKie.setMaxAge(MiaoshaUserService.TOKEN_EXPIRE);
        cooKie.setPath("/");
        response.addCookie(cooKie);

    }
}
