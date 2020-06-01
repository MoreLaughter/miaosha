package com.istudy.service.impl;

import com.alibaba.fastjson.JSON;
import com.istudy.mapper.MiaoshaOrderMapper;
import com.istudy.mapper.OrderInfoMapper;
import com.istudy.pojo.GoodsCustom;
import com.istudy.pojo.MiaoshaOrder;
import com.istudy.pojo.MiaoshaUser;
import com.istudy.pojo.OrderInfo;
import com.istudy.redis.OrderKey;
import com.istudy.service.MiaoshaOrderService;
import com.istudy.utils.RedisOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;

@Service
public class MiaoshaOrderServiceImpl implements MiaoshaOrderService{
    @Autowired
    OrderInfoMapper orderInfoMapper;

    @Autowired
    MiaoshaOrderMapper miaoshaOrderMapper;

    @Autowired
    RedisOperator redis;

    @Override
    public MiaoshaOrder selectMiaoshaOrderByUserIdAndGoodsId(long userId,long goodsId){
        return miaoshaOrderMapper.selectByUserIdAndGoodsId(userId,goodsId);

    }

    @Override
    @Transactional
    public OrderInfo createOrder(MiaoshaUser user, GoodsCustom goods){
        //生成订单信息
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setCreateDate(new Date());
        orderInfo.setDeliveryAddId(1L);
        orderInfo.setGoodsCount(1);
        orderInfo.setGoodsId(goods.getId());
        orderInfo.setGoodsName(goods.getGoodsName());
        orderInfo.setGoodsPrice(new BigDecimal(goods.getMiaoshaPrice()));
        orderInfo.setOrderChannel(1);
        orderInfo.setStatus(0);
        orderInfo.setUserId(user.getId());
        orderInfoMapper.insertAndReturnId(orderInfo);
        long insertId = orderInfo.getId();

        //生成秒杀订单信息
        MiaoshaOrder miaoshaOrder = new MiaoshaOrder();
        miaoshaOrder.setGoodsId(goods.getId());
        miaoshaOrder.setOrderId(insertId);
        miaoshaOrder.setUserId(user.getId());
        miaoshaOrderMapper.insert(miaoshaOrder);
        //生成订单缓存
        redis.set(OrderKey.getMiaoshaOrderByUseIdAndGoodsId.getPrefix()+user.getId()+"-"+goods.getId(),
                JSON.toJSONString(miaoshaOrder));
        return  orderInfo;
    }

}
