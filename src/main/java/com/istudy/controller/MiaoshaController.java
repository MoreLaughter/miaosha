package com.istudy.controller;

import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSON;
import com.istudy.Access.AccessLimit;
import com.istudy.pojo.GoodsCustom;
import com.istudy.pojo.MiaoshaOrder;
import com.istudy.pojo.MiaoshaUser;
import com.istudy.pojo.OrderInfo;
import com.istudy.rabbitmq.MQSender;
import com.istudy.rabbitmq.MiaoshaMessage;
import com.istudy.redis.GoodsKey;
import com.istudy.redis.OrderKey;
import com.istudy.result.CodeMsg;
import com.istudy.result.Result;
import com.istudy.service.GoodsService;
import com.istudy.service.MiaoshaOrderService;
import com.istudy.service.MiaoshaService;
import com.istudy.utils.JsonUtils;
import com.istudy.utils.RedisOperator;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/miaosha")
public class MiaoshaController implements InitializingBean{
    @Autowired
    GoodsService goodsService;

    @Autowired
    MiaoshaService miaoshaService;

    @Autowired
    MiaoshaOrderService miaoshaOrderService;

    @Autowired
    RedisOperator redis;

    @Autowired
    MQSender sender;
    private HashMap<Long, Boolean> localOverMap = new HashMap<Long, Boolean>();

    /**
     * 系统初始化
     * @throws Exception
     */
    public void afterPropertiesSet() throws Exception {
        List<GoodsCustom> goodsList = goodsService.queryGoodsCutom();
        if(goodsList == null){
            return;
        }
        for(GoodsCustom goodsCustom:goodsList){
            redis.set(GoodsKey.getMiaoshaGoodsStock.getPrefix() +":"+goodsCustom.getId(),String.valueOf(goodsCustom.getStockCount()));
            //false:
            localOverMap.put(goodsCustom.getId(),false);
        }
    }

    @AccessLimit(seconds=5, maxCount=4, needLogin=true)
    @RequestMapping(value = "/{path}/do_miaosha",method = RequestMethod.POST)
    @ResponseBody
    public Result<Integer> miaosha(Model model, MiaoshaUser user, @RequestParam("goodsId") long goodsId,
        @PathVariable("path") String path){
        model.addAttribute("user",user);
        if(user == null){
            return Result.error(CodeMsg.SESSION_ERROR);
        }
        //验证path
        boolean check = miaoshaService.checkPath(user,goodsId,path);
        if(!check){
            return Result.error(CodeMsg.REQUEST_ILLEGAL);
        }

        //内存标记，减少redis访问
        boolean over = localOverMap.get(goodsId);
        if(over){
            return Result.error(CodeMsg.REPEATED_MIAOSHA);
        }

        //预减库存
        long stock = redis.incr(GoodsKey.getMiaoshaGoodsStock.getPrefix() +":"+goodsId,-1);
        if(stock < 0){
            return Result.error(CodeMsg.STOCK_NULL);
        }
        //判断库存
       /* GoodsCustom goodsCustom = goodsService.queryGoodsCutomById(goodsId);
        int stock = goodsCustom.getGoodsStock();
        if(stock <= 0){
            return Result.error(CodeMsg.STOCK_NULL);

        }*/
        //判断是否已经秒杀，限购一次
        //先从缓存获取
        MiaoshaOrder order = JSON.parseObject(redis.get(OrderKey.getMiaoshaOrderByUseIdAndGoodsId.
                getPrefix()+user.getId()+"-"+goodsId),MiaoshaOrder.class);
        if(order != null){
            return Result.error(CodeMsg.REPEATED_MIAOSHA);
        }

        //入队
        MiaoshaMessage message = new MiaoshaMessage();
        message.setMiaoshaUser(user);
        message.setGoodsId(goodsId);
        sender.sendMessage(message);
        return Result.success(0);
        //减库存，下订单，写入秒杀订单
        /*OrderInfo orderInfo = miaoshaService.miaosha(user,new GoodsCustom());
        return Result.success(orderInfo);*/
    }

    @RequestMapping(value="/result", method=RequestMethod.GET)
    @ResponseBody
    public Result<Long> miaoshaResult(Model model,MiaoshaUser user,
                                      @RequestParam("goodsId")long goodsId) {
        model.addAttribute("user", user);
        if(user == null) {
            return Result.error(CodeMsg.SESSION_ERROR);
        }
        long result  =miaoshaService.getMiaoshaResult(user.getId(), goodsId);
        return Result.success(result);
    }

    @AccessLimit(seconds=5, maxCount=4, needLogin=true)
    @RequestMapping(value = "/path", method = RequestMethod.GET)
    @ResponseBody
    public Result<String> getMiaoshaPath(HttpServletRequest request, MiaoshaUser user,
                                         @RequestParam("goodsId") long goodsId) {
        if (user == null) {
            return Result.error(CodeMsg.SESSION_ERROR);
        }
        /*boolean check = miaoshaService.checkVerifyCode(user, goodsId, verifyCode);
        if (!check) {
            return Result.error(CodeMsg.REQUEST_ILLEGAL);
        }*/
        String path = miaoshaService.createMiaoshaPath(user, goodsId);
        return Result.success(path);
    }

}
