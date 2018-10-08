package com.erp.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.erp.service.CountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
    @RequestMapping("/count")
public class CountController
{
    @Autowired
    private CountService countService;

    @RequestMapping("/toReport")
    public String toReport(){
        return "/WEB-INF/jsp/report_order.jsp";
    }

    @RequestMapping("/toTrend")
    public String toTrend(){
        return "/WEB-INF/jsp/report_trend.jsp";
    }

    @RequestMapping(value = "/orderReport",produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String  orderReport(String startDate,String endDate){
        List list = countService.orderReport(startDate,endDate);
        return JSON.toJSONString(list);
    }

    @RequestMapping(value = "/trendReport",produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String trendReport(Integer years){
        List<Map<String, Object>> sumMoney = countService.getSumMoney(years);
        return JSON.toJSONString(sumMoney, SerializerFeature.WriteMapNullValue,SerializerFeature.DisableCircularReferenceDetect);
    }
}
