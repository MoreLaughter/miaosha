package com.istudy.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.*;

public class Goods implements Serializable{
    /**
     * 商品id
     */
    @Id
    private Long id;

    /**
     * 商品名称
     */
    @Column(name = "goods_name")
    private String goodsName;

    /**
     * 商品标题
     */
    @Column(name = "goods_title")
    private String goodsTitle;

    /**
     * 商品的图片
     */
    @Column(name = "goods_img")
    private String goodsImg;

    /**
     * 商品单价
     */
    @Column(name = "goods_price")
    private BigDecimal goodsPrice;

    /**
     * 商品库存，-1表示没有限制
     */
    @Column(name = "goods_stock")
    private Integer goodsStock;

    /**
     * 商品的详情介绍
     */
    @Column(name = "goods_detail")
    private String goodsDetail;

    /**
     * 获取商品id
     *
     * @return id - 商品id
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置商品id
     *
     * @param id 商品id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取商品名称
     *
     * @return goods_name - 商品名称
     */
    public String getGoodsName() {
        return goodsName;
    }

    /**
     * 设置商品名称
     *
     * @param goodsName 商品名称
     */
    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    /**
     * 获取商品标题
     *
     * @return goods_title - 商品标题
     */
    public String getGoodsTitle() {
        return goodsTitle;
    }

    /**
     * 设置商品标题
     *
     * @param goodsTitle 商品标题
     */
    public void setGoodsTitle(String goodsTitle) {
        this.goodsTitle = goodsTitle;
    }

    /**
     * 获取商品的图片
     *
     * @return goods_img - 商品的图片
     */
    public String getGoodsImg() {
        return goodsImg;
    }

    /**
     * 设置商品的图片
     *
     * @param goodsImg 商品的图片
     */
    public void setGoodsImg(String goodsImg) {
        this.goodsImg = goodsImg;
    }

    /**
     * 获取商品单价
     *
     * @return goods_price - 商品单价
     */
    public BigDecimal getGoodsPrice() {
        return goodsPrice;
    }

    /**
     * 设置商品单价
     *
     * @param goodsPrice 商品单价
     */
    public void setGoodsPrice(BigDecimal goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    /**
     * 获取商品库存，-1表示没有限制
     *
     * @return goods_stock - 商品库存，-1表示没有限制
     */
    public Integer getGoodsStock() {
        return goodsStock;
    }

    /**
     * 设置商品库存，-1表示没有限制
     *
     * @param goodsStock 商品库存，-1表示没有限制
     */
    public void setGoodsStock(Integer goodsStock) {
        this.goodsStock = goodsStock;
    }

    /**
     * 获取商品的详情介绍
     *
     * @return goods_detail - 商品的详情介绍
     */
    public String getGoodsDetail() {
        return goodsDetail;
    }

    /**
     * 设置商品的详情介绍
     *
     * @param goodsDetail 商品的详情介绍
     */
    public void setGoodsDetail(String goodsDetail) {
        this.goodsDetail = goodsDetail;
    }
}