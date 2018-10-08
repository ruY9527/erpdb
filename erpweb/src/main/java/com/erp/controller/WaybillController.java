package com.erp.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.erp.service.WaybillService;
import com.erp.utils.JsonUtils;
import com.erp.utils.MyResult;
import com.yang.erp.pojo.EasyResult;
import com.yang.erp.pojo.WayBill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/waybill")
public class WaybillController
{
    @Autowired
    private WaybillService waybillService;

    @RequestMapping("/toWaybill")
    public String toWatbill(){
        return "/admin/waybill.jsp";
    }

    //查询全部或者根据条件查询
    @RequestMapping(value = "/findAll",produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String findAll(WayBill wayBill,Integer page,Integer rows){
        List<WayBill> wayBillList =  waybillService.findAll(wayBill,page,rows);
        EasyResult<WayBill> easyResult = new EasyResult<WayBill>();
        easyResult.setRows(wayBillList);
        //获取全部个数
        Integer count =  waybillService.selectCount();
        easyResult.setTotal(count);
        return JSON.toJSONString(easyResult, SerializerFeature.WriteMapNullValue,SerializerFeature.DisableCircularReferenceDetect);
    }

    //添加
    @RequestMapping(value = "/add",produces = "text/json;charset=UTF-8",method = RequestMethod.POST)
    @ResponseBody
    public String add(WayBill wayBill){
        try{
            waybillService.add(wayBill);
            return JSON.toJSONString(new MyResult(true,"添加成功"));
        }catch (Exception e){
            return JSON.toJSONString(new MyResult(true,"添加成功"));
        }
    }

    //根据su获取一个对象便于修改
    @RequestMapping(value = "/getPojoById",produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String getPojoById(@RequestParam("id") Long sn){
        WayBill wayBill =  waybillService.getPojoById(sn);
        return JSON.toJSONString(wayBill,SerializerFeature.WriteMapNullValue,SerializerFeature.DisableCircularReferenceDetect);
    }

    //修改
    @RequestMapping(value = "/update",produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String update(WayBill wayBill){
        try
        {
            waybillService.update(wayBill);
            return JsonUtils.objectToJson(new MyResult(false,"修改成功"));
        }catch (Exception e){
            e.printStackTrace();
            return JsonUtils.objectToJson(new MyResult(false,"修改失败"));
        }
    }

    //虚拟删除
    @RequestMapping(value = "/delete",produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String delete(@RequestParam("id") Long sn){
        try{
            waybillService.delete(sn);
            return JsonUtils.objectToJson(new MyResult(true,"删除成功"));
        }catch (Exception e){
            e.printStackTrace();
            return JsonUtils.objectToJson(new MyResult(false,"删除失败"));
        }
    }


}
