package com.istudy.pojo;

public class OrderDetailVo {
	private GoodsCustom goods;
	private OrderInfo order;

    public GoodsCustom getGoods() {
        return goods;
    }

    public void setGoods(GoodsCustom goods) {
        this.goods = goods;
    }

    public OrderInfo getOrder() {
        return order;
    }

    public void setOrder(OrderInfo order) {
        this.order = order;
    }
}
