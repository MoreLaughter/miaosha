package com.istudy.mapper;

import com.istudy.pojo.MiaoshaOrder;
import com.istudy.utils.MyMapper;
import org.apache.ibatis.annotations.Param;

public interface MiaoshaOrderMapper extends MyMapper<MiaoshaOrder> {
    public MiaoshaOrder selectByUserIdAndGoodsId(@Param("userId") long userId,@Param("goodsId") long goodsId);
}