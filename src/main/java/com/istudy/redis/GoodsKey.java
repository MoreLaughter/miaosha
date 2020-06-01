package com.istudy.redis;

import com.istudy.pojo.Goods;

public class GoodsKey extends BasePrefix{


	public GoodsKey(String prefix) {
		super(prefix);
	}

	public GoodsKey(int expireSeconds, String prefix) {
		super(expireSeconds, prefix);
	}
	public static GoodsKey getGoodsList = new GoodsKey(60,"gl");
	public static GoodsKey getGoodsDetail = new GoodsKey(60,"gd");
	public static GoodsKey getMiaoshaGoodsStock = new GoodsKey("msstock");
}
