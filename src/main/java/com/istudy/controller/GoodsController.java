package com.istudy.controller;

import com.istudy.pojo.Goods;
import com.istudy.pojo.GoodsCustom;
import com.istudy.pojo.MiaoshaUser;
import com.istudy.service.GoodsService;
import com.istudy.service.MiaoshaUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    GoodsService goodsService;

    @RequestMapping("/tolist")
    public String toList(Model model, MiaoshaUser user){
        model.addAttribute("user",user);

        //查询商品列表
        List<GoodsCustom> goods = goodsService.queryGoodsCutom();
        model.addAttribute("goodsList",goods);
        return "goods_list";
    }

    @RequestMapping("/todetail/{goodsId}")
    public String toDetail(Model model, MiaoshaUser user, @PathVariable("goodsId") long goodsId){
        model.addAttribute("user",user);
        //查询秒杀商品详细信息
        GoodsCustom goodsCustom = goodsService.queryGoodsCutomById(goodsId);
        model.addAttribute("goods",goodsCustom);
        long startTime = goodsCustom.getStartDate().getTime();
        long endTime = goodsCustom.getEndDate().getTime();
        long currentTime = System.currentTimeMillis();
        //秒杀状态 0表示未开始  1表示进行中  2表示已经结束
        int miaoshaStatus = 0;
        //剩余时间
        int remainSeconds = 0;
        if(currentTime < startTime){
            miaoshaStatus = 0;
            remainSeconds = (int)((startTime - currentTime)/1000);

        } else if(currentTime > endTime){
            miaoshaStatus = 2;
            remainSeconds = -1;
        } else {
            miaoshaStatus = 1;
            remainSeconds = 0;
        }
        model.addAttribute("miaoshaStatus",miaoshaStatus);
        model.addAttribute("remainSeconds",remainSeconds);
        return "goods_detail";
    }

}
