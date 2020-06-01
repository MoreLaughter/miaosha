package com.istudy.service.impl;

import com.istudy.mapper.OrderInfoMapper;
import com.istudy.pojo.OrderInfo;
import com.istudy.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    OrderInfoMapper orderInfoMapper;

    @Override
    public OrderInfo getOrderById(long id) {
        return orderInfoMapper.selectByPrimaryKey(id);
    }
}
