package com.istudy.controller;

import com.alibaba.druid.support.json.JSONUtils;
import com.istudy.pojo.GoodsCustom;
import com.istudy.pojo.MiaoshaUser;
import com.istudy.redis.GoodsKey;
import com.istudy.service.GoodsService;
import com.istudy.utils.RedisOperator;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    GoodsService goodsService;

    @Autowired
    RedisOperator redis;

    @Autowired
    ThymeleafViewResolver thymeleafViewResolver;

    @ResponseBody
    @RequestMapping(value = "/tolist",produces = "text/html")
    public String toList(HttpServletRequest request, HttpServletResponse response,Model model, MiaoshaUser user){
        model.addAttribute("user",user);

        //取缓存
        String html = redis.get(GoodsKey.getGoodsList.getPrefix());
        //System.out.println(html.length() + ":" + html)  ;
        if(null != html){
            return html;
        }
        //查询商品列表
        List<GoodsCustom> goods = goodsService.queryGoodsCutom();
        model.addAttribute("goodsList",goods);

        //手动渲染
        WebContext webContext = new WebContext(request, response, request.getServletContext(), request.getLocale(),model.asMap());
        html = thymeleafViewResolver.getTemplateEngine().process("goods_list", webContext);
        if(!StringUtils.isEmpty(html)){
            redis.set(GoodsKey.getGoodsList.getPrefix(),html);
        }
        return html;


    }

    @ResponseBody
    @RequestMapping(value = "/todetail/{goodsId}",produces="text/html")
    public String toDetail(HttpServletRequest request, HttpServletResponse response,Model model, MiaoshaUser user,
                           @PathVariable("goodsId") long goodsId){
        model.addAttribute("user",user);
        //取缓存
        String html = redis.get(GoodsKey.getGoodsDetail.getPrefix()+goodsId);
        if (null != html){
            return html;
        }
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

        //手动渲染
        WebContext webContext = new WebContext(request, response, request.getServletContext(), request.getLocale(),model.asMap());
        html = thymeleafViewResolver.getTemplateEngine().process("goods_detail", webContext);
        if(!StringUtils.isEmpty(html)){
            redis.set(GoodsKey.getGoodsDetail.getPrefix()+goodsId,html);
        }
        return html;
    }

}
