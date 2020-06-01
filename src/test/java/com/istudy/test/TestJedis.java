package com.istudy.test;

import com.istudy.redis.GoodsKey;
import com.istudy.utils.RedisOperator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import redis.clients.jedis.Jedis;



public class TestJedis {
    @Autowired
    RedisTemplate redis;

    @Test

    public void testJedisSingle(){
        Jedis jedis = new Jedis("121.36.225.112",6379);
        jedis.set("sty","test");
        String name = jedis.get("sty");
        System.out.println(name);
        jedis.close();
    }




}
