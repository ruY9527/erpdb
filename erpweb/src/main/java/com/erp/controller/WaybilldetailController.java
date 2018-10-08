package com.erp.controller;

import com.alibaba.fastjson.JSON;
import com.erp.service.WaybilldetailService;
import com.yang.erp.pojo.WayBillDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/waybilldetail")
public class WaybilldetailController
{
    @Autowired
    private WaybilldetailService waybilldetailService;

    @RequestMapping(value="findBySn",produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String findBySn(Long sn){
        WayBillDetail wayBillDetail =  waybilldetailService.findBySn(sn);
        return JSON.toJSONString(wayBillDetail);
    }


}
