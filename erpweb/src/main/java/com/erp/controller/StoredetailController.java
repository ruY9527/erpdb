package com.erp.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.erp.service.StoredetailService;
import com.yang.erp.pojo.StoreDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/storedetail")
public class StoredetailController
{
    @Autowired
    private StoredetailService storedetailService;

    //跳转到storedetail详情页面的操作(库存)
    @RequestMapping("/toStoredetail")
    public String toStoredetail(){
        return "/WEB-INF/jsp/storedetail.jsp";
    }

    @RequestMapping("/findAll")
    @ResponseBody
    public String findAll(StoreDetail storeDetail){
        List<StoreDetail> storeDetailList =  storedetailService.findAll(storeDetail);
        return JSON.toJSONString(storeDetailList, SerializerFeature.DisableCircularReferenceDetect,SerializerFeature.WriteMapNullValue);
    }


}
