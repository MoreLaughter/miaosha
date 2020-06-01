package com.istudy.rabbitmq;

import com.istudy.pojo.Goods;
import com.istudy.pojo.GoodsCustom;
import com.istudy.pojo.MiaoshaOrder;
import com.istudy.pojo.MiaoshaUser;
import com.istudy.service.GoodsService;
import com.istudy.service.MiaoshaOrderService;
import com.istudy.service.MiaoshaService;
import com.istudy.utils.ConvertUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class MQReciever {
    @Autowired
    GoodsService goodsService;

    @Autowired
    MiaoshaOrderService orderService;

    @Autowired
    MiaoshaService miaoshaService;

    public static final Logger log = LoggerFactory.getLogger(MQReciever.class);

    @RabbitListener(queues = MQConfig.MIAOSHA_QUEUE )
    public void receive(String message){
        log.info("receive miaoshamessage:"+message);
        MiaoshaMessage mm  = ConvertUtil.stringToBean(message, MiaoshaMessage.class);
        MiaoshaUser user = mm.getMiaoshaUser();
        long goodsId = mm.getGoodsId();
        GoodsCustom goods = goodsService.queryGoodsCutomById(goodsId);
        int stock = goods.getStockCount();
        if(stock <= 0) {
            return;
        }
        //判断是否已经秒杀到了
        MiaoshaOrder order = orderService.selectMiaoshaOrderByUserIdAndGoodsId(user.getId(), goodsId);
        if(order != null) {
            return;
        }
        //减库存 下订单 写入秒杀订单
        miaoshaService.miaosha(user, goods);
    }

    /*@RabbitListener(queues=MQConfig.QUEUE)
    public void receive(String message){
        log.info("receieve message:" + message);
    }

    @RabbitListener(queues=MQConfig.TOPIC_QUEUE1)
    public void receiveTopic1(String message) {
        log.info(" topic  queue1 message:"+message);
    }

    @RabbitListener(queues=MQConfig.TOPIC_QUEUE2)
    public void receiveTopic2(String message) {
        log.info(" topic  queue2 message:"+message);
    }

    @RabbitListener(queues=MQConfig.HEADER_QUEUE)
    public void receiveHeaderQueue(byte[] message) {
        log.info(" header  queue message:"+new String(message));
    }*/
}
