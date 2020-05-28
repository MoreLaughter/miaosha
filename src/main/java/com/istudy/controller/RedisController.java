package com.istudy.controller;

import com.alibaba.fastjson.JSON;
import com.istudy.pojo.IMoocJSONResult;
import com.istudy.pojo.User;
import com.istudy.utils.JsonUtils;
import com.istudy.utils.RedisOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/redis")
public class RedisController {
	
	@Autowired
	private StringRedisTemplate strRedis;
	
	@Autowired
	private RedisOperator redis;
	
	@RequestMapping("/test")
	public IMoocJSONResult test() {
		
		strRedis.opsForValue().set("imooc-cache", "hello 慕课网~~~~~~");

		User u = new User();
		u.setId(111);
		u.setName("dshs");
		strRedis.opsForValue().set("json:user", JSON.toJSONString(u));
		User jsonUser = JSON.toJavaObject(JSON.parseObject(redis.get("json:user")), User.class);
		return IMoocJSONResult.ok(jsonUser);
	}

	@RequestMapping("/set")
	public IMoocJSONResult set() {
	    redis.set("stu2","haha");


		/*strRedis.opsForValue().set("imooc-cache", "hello 慕课网~~~~~~");

		User u = new User();
		u.setId(111);
		u.setName("dshs");
		strRedis.opsForValue().set("json:user", JsonUtils.objectToJson(u));*/
		//User jsonUser = JsonUtils.jsonToPojo(strRedis.opsForValue().get("json:user"), User.class);
		return IMoocJSONResult.ok();
	}

	@RequestMapping("/get")
    public IMoocJSONResult get(){
		String result = redis.get("stu2");
	    return IMoocJSONResult.ok(result);
    }

	
	@RequestMapping("/getJsonList")
	public IMoocJSONResult getJsonList() {
		
		User user = new User();
		user.setId(11);
		user.setName("haha");

		User user1 = new User();
		user1.setId(11);
		user1.setName("haha222");
		
		List<User> userList = new ArrayList<>();
		userList.add(user);
		userList.add(user1);
		redis.set("json:info:userlist", JsonUtils.objectToJson(userList), 5000);
		String userListJson = redis.get("json:info:userlist");
		List<User> userListBorn = JsonUtils.jsonToList(userListJson, User.class);
		return IMoocJSONResult.ok(userListBorn);
	}
}