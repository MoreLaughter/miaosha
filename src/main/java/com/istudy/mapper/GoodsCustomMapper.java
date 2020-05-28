package com.istudy.mapper;

import com.istudy.pojo.GoodsCustom;

import java.util.List;

public interface GoodsCustomMapper {
    public List<GoodsCustom> queryGoodsCutom();
    public GoodsCustom queryGoodsCutomById(long id);

}