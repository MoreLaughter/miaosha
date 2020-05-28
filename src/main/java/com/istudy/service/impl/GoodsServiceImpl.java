package com.istudy.service.impl;

import com.istudy.mapper.GoodsCustomMapper;
import com.istudy.mapper.GoodsMapper;
import com.istudy.pojo.Goods;
import com.istudy.pojo.GoodsCustom;
import com.istudy.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {
    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private GoodsCustomMapper goodsCustomMapper;

    @Override
    public List<Goods> getAllGoods() {
        List<Goods> goods = goodsMapper.selectAll();
        return goods;
    }

    @Override
    public Goods getGoodsById(long id) {
        return goodsMapper.selectByPrimaryKey(id);
    }


    @Override
    public List<GoodsCustom> queryGoodsCutom() {
        List<GoodsCustom> goodsCustoms = goodsCustomMapper.queryGoodsCutom();
        return goodsCustoms;
    }

    @Override
    public GoodsCustom queryGoodsCutomById(long id) {
        GoodsCustom goodsCustoms = goodsCustomMapper.queryGoodsCutomById(id);
        return goodsCustoms;
    }
}
