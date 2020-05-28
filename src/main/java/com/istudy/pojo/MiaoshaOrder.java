package com.istudy.pojo;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "miaosha_order")
public class MiaoshaOrder implements Serializable {
    /**
     * 秒杀订单表
     */
    @Id
    private Long id;

    /**
     * 用户id
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * 商品id
     */
    @Column(name = "order_id")
    private Long orderId;

    /**
     * 商品id
     */
    @Column(name = "goods_id")
    private Long goodsId;

    /**
     * 获取秒杀订单表
     *
     * @return id - 秒杀订单表
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置秒杀订单表
     *
     * @param id 秒杀订单表
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取用户id
     *
     * @return user_id - 用户id
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * 设置用户id
     *
     * @param userId 用户id
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 获取商品id
     *
     * @return order_id - 商品id
     */
    public Long getOrderId() {
        return orderId;
    }

    /**
     * 设置商品id
     *
     * @param orderId 商品id
     */
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    /**
     * 获取商品id
     *
     * @return goods_id - 商品id
     */
    public Long getGoodsId() {
        return goodsId;
    }

    /**
     * 设置商品id
     *
     * @param goodsId 商品id
     */
    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }


}