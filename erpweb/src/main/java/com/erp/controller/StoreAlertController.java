package com.erp.controller;

import com.alibaba.fastjson.JSON;
import com.erp.service.StoreAlertService;
import com.erp.utils.MyResult;
import com.yang.erp.pojo.StoreAlert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/storeAlert")
public class StoreAlertController
{
    @Autowired
    private StoreAlertService storeAlertService;

    //去库存警报页面
    @RequestMapping("/toStoreAlert")
    public String toStoreAlert(){
        return "/WEB-INF/jsp/storeAlert.jsp";
    }

    @RequestMapping(value = "/findAll",produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String findAll(){
        List<StoreAlert> all = storeAlertService.findAll();
        if(all !=null && all.size()>0){
            return JSON.toJSONString(all);
        } else {
            return "仓库数量足够";
        }
    }

    //发送预警邮箱的操作
    @RequestMapping(value = "/sendMessage",produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String sendMessage(){
        try{
            MyResult myResult = storeAlertService.sendStoreWarm();
             return JSON.toJSONString(myResult);
        } catch (Exception e){
            e.printStackTrace();
            return JSON.toJSONString(new MyResult(false,"项目邮箱停止"));
        }
    }

}
