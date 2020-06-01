package com.istudy.test;

import com.alibaba.fastjson.JSON;
import com.istudy.pojo.MiaoshaOrder;
import com.istudy.pojo.MiaoshaUser;

public class Test {
    @org.junit.Test
    public void test01(){
        MiaoshaUser user = new MiaoshaUser();
        user.setPassword("dsds");
        System.out.println(user.toString());
        System.out.println(JSON.toJSONString(user));
    }
}
