package com.erp.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.erp.service.ReturnOrdersService;
import com.erp.utils.MyResult;
import com.yang.erp.pojo.Emp;
import com.yang.erp.pojo.Goods;
import com.yang.erp.pojo.ReturnOrders;
import com.yang.erp.pojo.ReturnOrdersDetail;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/returnOrders")
public class ReturnOrdersController
{

    @Autowired
    private ReturnOrdersService returnOrdersService;

    //去往退货页面
    @RequestMapping("/toReturnOrders")
    public String toReturnOrders(){
        return  "/WEB-INF/jsp/returnorders.jsp";
    }

    @RequestMapping("/add")
    @ResponseBody
    public MyResult add(String type,String supplierId,String json){
        //type类型   supplierId 供应商id   json商品信息
        Emp emp = (Emp) SecurityUtils.getSubject().getPrincipal();
        if(emp == null){
            return new MyResult(false,"请你选登录");
        }
        List<ReturnOrdersDetail> returnOrdersDetailList =  JSON.parseArray(json, ReturnOrdersDetail.class);
        try{
            MyResult myresult = returnOrdersService.add(emp.getEid(), type, supplierId, returnOrdersDetailList);
            return myresult;
        }catch (Exception e){
            e.printStackTrace();
            return new MyResult(false,"申请失败");
        }
    }

    //获取全部
    @RequestMapping("/findByType")
    @ResponseBody
    public String findByType(String type,String state){
        List<ReturnOrders> returnOrdersList =  returnOrdersService.findByType(type,state);
        return JSON.toJSONString(returnOrdersList, SerializerFeature.WriteMapNullValue,SerializerFeature.DisableCircularReferenceDetect);
    }

    //我的退货订单查询
    @RequestMapping("/myFindByType")
    @ResponseBody
    public String myFindByType(String type){
        //获取当前登录的信息
        Emp emp = (Emp) SecurityUtils.getSubject().getPrincipal();
        if(emp == null){
            return "请你先登录";
        }
        List<ReturnOrders> returnOrdersList =  returnOrdersService.myFindByType(emp.getEid(),type);
        return JSON.toJSONString(returnOrdersList,SerializerFeature.WriteMapNullValue,SerializerFeature.DisableCircularReferenceDetect);
    }

    //审核（退货订单审核）
    @RequestMapping("/doCheck")
    @ResponseBody
    public MyResult doCheck(Long roid){
        Emp emp = (Emp) SecurityUtils.getSubject().getPrincipal();
        if(emp == null){
            return new MyResult(false,"请你先登录");
        }
        try{
            MyResult myResult =  returnOrdersService.doCheck(emp.getEid(),roid);
            return myResult;
        }catch (Exception e){
            e.printStackTrace();
            return new MyResult(false,"审核失败");
        }
    }

    //出库的确定
    @RequestMapping("/outBank")
    @ResponseBody
    public MyResult outBank(Long rosd,Long storeId){
        //先判断是否登录
        Emp emp = (Emp) SecurityUtils.getSubject().getPrincipal();
        if(emp == null){
            return new MyResult(false,"请你先登录");
        }
        //rosd返回详细信息的id,storeId库存id
        try {
            MyResult myResult =  returnOrdersService.outBank(rosd,storeId,emp.getEid());
            return myResult;
        }catch (Exception e){
            e.printStackTrace();
            return new MyResult(false,"出库失败");
        }
    }

}
