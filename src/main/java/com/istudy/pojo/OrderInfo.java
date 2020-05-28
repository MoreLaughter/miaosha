package com.istudy.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "order_info")
public class OrderInfo implements Serializable {
    /**
     * 订单表
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
    @Column(name = "goods_id")
    private Long goodsId;

    /**
     * 收获地址id
     */
    @Column(name = "delivery_add_id")
    private Long deliveryAddId;

    /**
     * 冗余过来的商品名称
     */
    @Column(name = "goods_name")
    private String goodsName;

    /**
     * 商品数量
     */
    @Column(name = "goods_count")
    private Integer goodsCount;

    /**
     * 商品单价
     */
    @Column(name = "goods_price")
    private BigDecimal goodsPrice;

    /**
     * 1pc,2android,3ios
     */
    @Column(name = "order_channel")
    private Byte orderChannel;

    /**
     * 订单状态：0新建未支付，1待发货，2已发货，3已收货，4已退款，5已完成
     */
    private Byte status;

    /**
     * 订单的创建时间
     */
    @Column(name = "create_date")
    private Date createDate;

    /**
     * 支付时间
     */
    @Column(name = "pay_date")
    private Date payDate;

    /**
     * 获取订单表
     *
     * @return id - 订单表
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置订单表
     *
     * @param id 订单表
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

    /**
     * 获取收获地址id
     *
     * @return delivery_add_id - 收获地址id
     */
    public Long getDeliveryAddId() {
        return deliveryAddId;
    }

    /**
     * 设置收获地址id
     *
     * @param deliveryAddId 收获地址id
     */
    public void setDeliveryAddId(Long deliveryAddId) {
        this.deliveryAddId = deliveryAddId;
    }

    /**
     * 获取冗余过来的商品名称
     *
     * @return goods_name - 冗余过来的商品名称
     */
    public String getGoodsName() {
        return goodsName;
    }

    /**
     * 设置冗余过来的商品名称
     *
     * @param goodsName 冗余过来的商品名称
     */
    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    /**
     * 获取商品数量
     *
     * @return goods_count - 商品数量
     */
    public Integer getGoodsCount() {
        return goodsCount;
    }

    /**
     * 设置商品数量
     *
     * @param goodsCount 商品数量
     */
    public void setGoodsCount(Integer goodsCount) {
        this.goodsCount = goodsCount;
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
     * 获取1pc,2android,3ios
     *
     * @return order_channel - 1pc,2android,3ios
     */
    public Byte getOrderChannel() {
        return orderChannel;
    }

    /**
     * 设置1pc,2android,3ios
     *
     * @param orderChannel 1pc,2android,3ios
     */
    public void setOrderChannel(Byte orderChannel) {
        this.orderChannel = orderChannel;
    }

    /**
     * 获取订单状态：0新建未支付，1待发货，2已发货，3已收货，4已退款，5已完成
     *
     * @return status - 订单状态：0新建未支付，1待发货，2已发货，3已收货，4已退款，5已完成
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * 设置订单状态：0新建未支付，1待发货，2已发货，3已收货，4已退款，5已完成
     *
     * @param status 订单状态：0新建未支付，1待发货，2已发货，3已收货，4已退款，5已完成
     */
    public void setStatus(Byte status) {
        this.status = status;
    }

    /**
     * 获取订单的创建时间
     *
     * @return create_date - 订单的创建时间
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * 设置订单的创建时间
     *
     * @param createDate 订单的创建时间
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * 获取支付时间
     *
     * @return pay_date - 支付时间
     */
    public Date getPayDate() {
        return payDate;
    }

    /**
     * 设置支付时间
     *
     * @param payDate 支付时间
     */
    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }
}