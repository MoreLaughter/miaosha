package com.istudy.service.impl;

import com.alibaba.fastjson.JSON;
import com.istudy.exception.GlobalException;
import com.istudy.mapper.MiaoshaUserMapper;
import com.istudy.pojo.MiaoshaUser;
import com.istudy.redis.MiaoshaUserKey;
import com.istudy.result.CodeMsg;
import com.istudy.service.MiaoshaUserService;
import com.istudy.utils.MD5Util;
import com.istudy.utils.RedisOperator;
import com.istudy.utils.UUIDUtil;
import com.istudy.vo.LoginVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Service
public class MiaoshaUserServiceImpl implements MiaoshaUserService{



    @Autowired
    private MiaoshaUserMapper miaoshaUserMapper;

    @Autowired
    private RedisOperator redis;

    @Override
    public  boolean doLogin(HttpServletResponse response, LoginVO loginVo){
        if(loginVo == null){
            throw new GlobalException(CodeMsg.LOGINVO_EMPTY);
        }
        String mobile = loginVo.getMobile();
        String password = loginVo.getPassword();
        //判断该手机号是否存在
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
        return true;

    }

    private void addCookie(HttpServletResponse response, String token, MiaoshaUser user) {
        redis.set(MiaoshaUserKey.token.getPrefix()+":"+token, JSON.toJSONString(user));
        Cookie cooKie = new Cookie(COOKI_NAME_TOKEN,token);
        cooKie.setMaxAge(TOKEN_EXPIRE);
        cooKie.setPath("/");
        response.addCookie(cooKie);

    }





}
