package com.istudy.pojo;

public class GoodsDetailVo {
    private GoodsCustom goodsCustom;
    private int miaoshaStatus;
    private int remainSeconds;
    private MiaoshaUser user;

    public GoodsCustom getGoodsCustom() {
        return goodsCustom;
    }

    public void setGoodsCustom(GoodsCustom goodsCustom) {
        this.goodsCustom = goodsCustom;
    }

    public int getMiaoshaStatus() {
        return miaoshaStatus;
    }

    public void setMiaoshaStatus(int miaoshaStatus) {
        this.miaoshaStatus = miaoshaStatus;
    }

    public int getRemainSeconds() {
        return remainSeconds;
    }

    public void setRemainSeconds(int remainSeconds) {
        this.remainSeconds = remainSeconds;
    }

    public MiaoshaUser getUser() {
        return user;
    }

    public void setUser(MiaoshaUser user) {
        this.user = user;
    }
}
