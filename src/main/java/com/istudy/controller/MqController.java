package com.istudy.controller;


import com.istudy.rabbitmq.MQReciever;
import com.istudy.rabbitmq.MQSender;
import com.istudy.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/mq")
public class MqController {

   /* @Autowired
    MQReciever reciever;

    @Autowired
    MQSender sender;

    @RequestMapping("/test")
    @ResponseBody
    public Result<String> mq(){
        sender.send("hello rabbitmq");
        return Result.success("hello rabbitmq");
    }

    @RequestMapping("/header")
    @ResponseBody
    public Result<String> header() {
		sender.sendHeader("hello,imooc");
        return Result.success("Hello，world");
    }

	@RequestMapping("/fanout")
    @ResponseBody
    public Result<String> fanout() {
		sender.sendFanout("hello,imooc");
        return Result.success("Hello，world");
    }

	@RequestMapping("/topic")
    @ResponseBody
    public Result<String> topic() {
		sender.sendTopic("hello,imooc");
        return Result.success("Hello，world");
    }*/
}
