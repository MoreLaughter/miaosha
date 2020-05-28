package com.istudy.service;

import com.istudy.pojo.Goods;
import com.istudy.pojo.GoodsCustom;

import java.util.List;

public interface GoodsService {
    public List<Goods> getAllGoods();
    public Goods getGoodsById(long id);
    public List<GoodsCustom> queryGoodsCutom();
    public GoodsCustom queryGoodsCutomById(long id);
}
