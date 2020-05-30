package com.istudy.service.impl;

import com.alibaba.fastjson.JSON;
import com.istudy.exception.GlobalException;
import com.istudy.mapper.MiaoshaUserMapper;
import com.istudy.pojo.MiaoshaUser;
import com.istudy.redis.MiaoshaUserKey;
import com.istudy.result.CodeMsg;
import com.istudy.service.MiaoshaUserService;
import com.istudy.utils.JsonUtils;
import com.istudy.utils.MD5Util;
import com.istudy.utils.RedisOperator;
import com.istudy.utils.UUIDUtil;
import com.istudy.vo.LoginVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class MiaoshaUserServiceImpl implements MiaoshaUserService{



    @Autowired
    private MiaoshaUserMapper miaoshaUserMapper;

    @Autowired
    private RedisOperator redis;

    @Override
    public  String doLogin(HttpServletResponse response, LoginVO loginVo){
        if(loginVo == null){
            throw new GlobalException(CodeMsg.LOGINVO_EMPTY);
        }
        String mobile = loginVo.getMobile();
        String password = loginVo.getPassword();
        //通过手机号查找这个用户
        //应该进行优化，使用对象缓存。getById()已经实现，但key设计还没完善
        //String token = request.getParameter(MiaoshaUserService.COOKI_NAME_TOKEN);
        MiaoshaUser user = miaoshaUserMapper.selectByPrimaryKey(Long.parseLong(mobile));
        if(user == null){
            throw new GlobalException(CodeMsg.USER_NOT_EXIST);
        }
        //b7797cce01b4b131b433b6acf4add449
        //验证密码
        String dbPass = user.getPassword();
        String saltDB = user.getSalt();
        String calcPass = MD5Util.formPassToDBPass(password, saltDB);
        /*if(!calcPass.equals(dbPass)) {
            throw new GlobalException(CodeMsg.PASSWORD_ERROR);
        }*/
        //生成cookie
        String token = UUIDUtil.uuid();
        addCookie(response, token, user);
        return token;

    }

    //先从redis中取缓存，若没有再去数据库中取
    private MiaoshaUser getById(String mobile){
        //先从缓存中取数据
        //逻辑待修改，redis中并未储存带有id的user（都是带token）的KEY
        String userStr = redis.get(MiaoshaUserKey.getById.getPrefix());
        if(null != userStr){
            return JsonUtils.jsonToPojo(userStr,MiaoshaUser.class);
        } else {
            return miaoshaUserMapper.selectByPrimaryKey(Long.parseLong(mobile));
        }


    }


    private void addCookie(HttpServletResponse response, String token, MiaoshaUser user) {
        redis.set(MiaoshaUserKey.token.getPrefix()+":"+token, JSON.toJSONString(user));
        Cookie cooKie = new Cookie(COOKI_NAME_TOKEN,token);
        cooKie.setMaxAge(TOKEN_EXPIRE);
        cooKie.setPath("/");
        response.addCookie(cooKie);

    }





}
