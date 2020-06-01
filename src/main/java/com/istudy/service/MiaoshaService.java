package com.istudy.service;

import com.istudy.pojo.GoodsCustom;
import com.istudy.pojo.MiaoshaOrder;
import com.istudy.pojo.MiaoshaUser;
import com.istudy.pojo.OrderInfo;
import com.istudy.redis.MiaoshaKey;
import com.istudy.redis.OrderKey;
import com.istudy.service.impl.MiaoshaOrderServiceImpl;
import com.istudy.utils.MD5Util;
import com.istudy.utils.RedisOperator;
import com.istudy.utils.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MiaoshaService {

    @Autowired
    MiaoshaGoodsService miaoshaGoodsService;

    @Autowired
    MiaoshaOrderServiceImpl miaoshaOrderService;

    @Autowired
    RedisOperator redis;

    public OrderInfo miaosha(MiaoshaUser user, GoodsCustom goodsCustom) {
        //减库存
        boolean success = miaoshaGoodsService.reduceStock(goodsCustom.getId());
        if(success){
            return miaoshaOrderService.createOrder(user, goodsCustom);
        } else {
            //库存不够，更新redis关于库存的缓存
            setGoodOver(goodsCustom.getId());
            return null;
        }


    }

    private void setGoodOver(Long id) {
        redis.set(MiaoshaKey.isStokNull.getPrefix()+":"+id,"0");
    }

    public long getMiaoshaResult(Long userId, long goodsId) {
        MiaoshaOrder order = miaoshaOrderService.selectMiaoshaOrderByUserIdAndGoodsId(userId, goodsId);
        if(order != null) {//秒杀成功
            return order.getOrderId();
        }else {
            boolean isOver = getGoodsOver(goodsId);
            if(isOver) {
                return -1;
            }else {
                return 0;
            }
        }
    }

    //从缓存中查看stock是否为0
    private boolean getGoodsOver(long goodsId) {
        String result = redis.get(MiaoshaKey.isStokNull.getPrefix() + ":" + goodsId);
        if (result == null || result.equals("0")) {
            return false;
        } else {
            return true;
        }
    }

    public String createMiaoshaPath(MiaoshaUser user, long goodsId) {
        if(user == null || goodsId <=0) {
            return null;
        }
        String str = MD5Util.md5(UUIDUtil.uuid()+"123456");
        redis.set(MiaoshaKey.getMiaoshaPath.getPrefix()+":"+user.getId() + "-"+ goodsId,str);
        return str;
    }

    public boolean checkPath(MiaoshaUser user, long goodsId, String path) {
        if(user == null || path == null){
            return false;
        }
        String pathOld = redis.get(MiaoshaKey.getMiaoshaPath.getPrefix()+":"+user.getId() + "-"+ goodsId);
        return path.equals(pathOld);
    }
}
