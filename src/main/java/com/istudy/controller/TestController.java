package com.istudy.controller;

import com.istudy.result.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/test")
public class TestController {


    @RequestMapping("/index")
    public String index(){
        return "login";
    }

    @RequestMapping("/hello")
    public String hello(){
        return "thymeleaf/test";
    }


    @RequestMapping("/result")
    @ResponseBody
    public Result result(){
        return Result.success(true);
    }

}
