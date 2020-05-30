package com.istudy.controller;

import com.istudy.result.Result;
import com.istudy.service.MiaoshaUserService;
import com.istudy.vo.LoginVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private MiaoshaUserService miaoshaUserService;

    private static Logger log = LoggerFactory.getLogger(LoginController.class);

    @RequestMapping("/tologin")
    public String toLogin(){
        return "login";
    }

    @ResponseBody
    @RequestMapping("/dologin")
        public Result<String> doLogin(HttpServletResponse response, @Valid LoginVO loginVo){
        log.info(loginVo.toString());
        //登录
        String token = miaoshaUserService.doLogin(response,loginVo);
        return Result.success(token);
    }



}
