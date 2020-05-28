package com.istudy.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "miaosha_goods")
public class MiaoshaGoods implements Serializable {
    /**
     * 秒杀的商品表
     */
    @Id
    private Long id;

    @Column(name = "goods_id")
    private Long goodsId;

    /**
     * 秒杀单价
     */
    @Column(name = "miaosha_price")
    private BigDecimal miaoshaPrice;

    /**
     * 库存数量
     */
    @Column(name = "stock_count")
    private Integer stockCount;

    /**
     * 秒杀开始时间
     */
    @Column(name = "start_date")
    private Date startDate;

    /**
     * 秒杀结束时间
     */
    @Column(name = "end_date")
    private Date endDate;

    /**
     * 获取秒杀的商品表
     *
     * @return id - 秒杀的商品表
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置秒杀的商品表
     *
     * @param id 秒杀的商品表
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return goods_id
     */
    public Long getGoodsId() {
        return goodsId;
    }

    /**
     * @param goodsId
     */
    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    /**
     * 获取秒杀单价
     *
     * @return miaosha_price - 秒杀单价
     */
    public BigDecimal getMiaoshaPrice() {
        return miaoshaPrice;
    }

    /**
     * 设置秒杀单价
     *
     * @param miaoshaPrice 秒杀单价
     */
    public void setMiaoshaPrice(BigDecimal miaoshaPrice) {
        this.miaoshaPrice = miaoshaPrice;
    }

    /**
     * 获取库存数量
     *
     * @return stock_count - 库存数量
     */
    public Integer getStockCount() {
        return stockCount;
    }

    /**
     * 设置库存数量
     *
     * @param stockCount 库存数量
     */
    public void setStockCount(Integer stockCount) {
        this.stockCount = stockCount;
    }

    /**
     * 获取秒杀开始时间
     *
     * @return start_date - 秒杀开始时间
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * 设置秒杀开始时间
     *
     * @param startDate 秒杀开始时间
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * 获取秒杀结束时间
     *
     * @return end_date - 秒杀结束时间
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * 设置秒杀结束时间
     *
     * @param endDate 秒杀结束时间
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}