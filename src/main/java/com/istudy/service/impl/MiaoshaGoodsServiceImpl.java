package com.istudy.service.impl;

import com.istudy.mapper.MiaoshaGoodsMapper;
import com.istudy.service.MiaoshaGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MiaoshaGoodsServiceImpl implements MiaoshaGoodsService{

    @Autowired
    MiaoshaGoodsMapper mapper;

    @Override
    public boolean reduceStock(long goodId) {
        return mapper.reduceStock(goodId);
    }


}
