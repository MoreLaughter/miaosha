package com.istudy.service.impl;

import com.istudy.mapper.UserMapper;
import com.istudy.pojo.User;
import com.istudy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;


    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public int inserUser() {
        User u = new User();
        u.setId(11);
        u.setName("haha");

        return userMapper.insert(u);
    }

    @Override
    public User getUser(int id) {
        User u = userMapper.selectByPrimaryKey(id);
        return u;
    }
}
