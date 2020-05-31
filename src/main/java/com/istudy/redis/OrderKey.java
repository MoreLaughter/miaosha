package com.istudy.redis;

public class OrderKey extends BasePrefix {

	public OrderKey( String prefix) {
		super(prefix);
	}

	public static OrderKey getMiaoshaOrderByUseIdAndGoodsId = new OrderKey("mso");
}
