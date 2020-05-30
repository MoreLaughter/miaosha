package com.istudy.service;

import com.istudy.vo.LoginVO;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public interface MiaoshaUserService {
    public static final int TOKEN_EXPIRE = 3600*24*2;
    public static final String COOKI_NAME_TOKEN = "token";
    public String doLogin(HttpServletResponse response, LoginVO vo);

}
