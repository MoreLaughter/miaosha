package com.istudy.mapper;

import com.istudy.pojo.MiaoshaGoods;
import com.istudy.utils.MyMapper;
import org.apache.ibatis.annotations.Param;

public interface MiaoshaGoodsMapper extends MyMapper<MiaoshaGoods> {
    public void reduceStock(@Param("goodsId") long goodId);
}