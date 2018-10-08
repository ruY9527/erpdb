package com.erp.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.erp.service.OrdersService;
import com.erp.service.OrdersdetailService;
import com.erp.utils.MyResult;
import com.yang.erp.pojo.Emp;
import com.yang.erp.pojo.Orders;
import com.yang.erp.pojo.Ordersdetail;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrdersController {

    @Autowired
    private OrdersdetailService ordersdetailService;

    @Autowired
    private OrdersService ordersService;

    @RequestMapping("/toOrders")
    public String toOrders(){
        return "/WEB-INF/jsp/orders.jsp";
    }



    @RequestMapping("/add")
    @ResponseBody
    public MyResult add(String type,String supplierId,String json/*,HttpSession session*/){
        //前端的json字符串转换为List对象
        List<Ordersdetail> ordersdetails = JSON.parseArray(json,Ordersdetail.class);
        //获取登录的用户
       /* Emp emp  =(Emp) session.getAttribute("loginUser");*/
        Emp emp = (Emp) SecurityUtils.getSubject().getPrincipal();
        if(emp == null){
            return new MyResult(false,"你还没登录");
        }
        try{
            //传递供应商id,创建人id当前登录的emp的id
            ordersdetailService.add(emp,supplierId,ordersdetails,type);
            return new MyResult(true,"添加成功");
        }catch (Exception e){
            e.printStackTrace();
            return new MyResult(false,"添加失败");
        }
    }

    //根据type类型来获取信息
    @RequestMapping(value = "/findByType",produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String findByType(String type,String state){
        List<Orders> ordersList = ordersService.findByType(type,state);
        return JSON.toJSONString(ordersList,SerializerFeature.WriteMapNullValue,SerializerFeature.DisableCircularReferenceDetect);
    }

    //根据oid修改state状态为已审核
    @RequestMapping("/doCheck")
    @ResponseBody
    public MyResult updateStateByOid(Long oid/*,HttpSession session*/){
       /* Emp emp = (Emp) session.getAttribute("loginUser");*/
        Emp emp = (Emp) SecurityUtils.getSubject().getPrincipal();
        if(emp == null){
            return new MyResult(false,"你还没登录");
        }
        try{
            //登录，对状态进行修改
            ordersService.updateStateByOid(emp.getEid(),oid);
            return new MyResult(true,"修改成功");
        } catch (Exception e){
            e.printStackTrace();
            return new MyResult(false,"修改失败");
        }
    }

    //只显示我的登录
    @RequestMapping("/myFindByType")
    @ResponseBody
    public String myFindByType(String type/*,HttpSession session*/){
        /*Emp emp =(Emp) session.getAttribute("loginUser");*/
        Emp emp = (Emp) SecurityUtils.getSubject().getPrincipal();
        if(emp == null){
            return "请你先登录";
        }
        List<Orders> orders =  ordersService.myFindByType(emp.getEid(),type);
        return JSON.toJSONString(orders,SerializerFeature.WriteMapNullValue,SerializerFeature.DisableCircularReferenceDetect);
    }

    //采购的确定操作
    @RequestMapping("/doStart")
    @ResponseBody
    public MyResult doStart(@RequestParam("id") Long oid/*, HttpSession session*/){
        /*Emp emp = (Emp) session.getAttribute("loginUser");*/
        Emp emp = (Emp) SecurityUtils.getSubject().getPrincipal();
        if(emp == null){
            return new MyResult(false,"请你先登录");
        }
        try{
            ordersService.doStart(emp.getEid(),oid);
            return new MyResult(true,"确定成功");
        }catch (Exception e){
            e.printStackTrace();
            return new MyResult(false,"确定失败");
        }
    }

}
