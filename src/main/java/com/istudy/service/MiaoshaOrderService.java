package com.istudy.service;

import com.istudy.pojo.GoodsCustom;
import com.istudy.pojo.MiaoshaOrder;
import com.istudy.pojo.MiaoshaUser;
import com.istudy.pojo.OrderInfo;

public interface MiaoshaOrderService {
    public MiaoshaOrder selectMiaoshaOrderByUserIdAndGoodsId(long userId, long goodsId);

    public OrderInfo createOrder(MiaoshaUser user, GoodsCustom goods);

}
