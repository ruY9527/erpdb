package com.erp.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.erp.service.StoreOperService;
import com.erp.utils.MyResult;
import com.yang.erp.pojo.EasyResult;
import com.yang.erp.pojo.Emp;
import com.yang.erp.pojo.StoreOper;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/storeOper")
public class StoreOperController {
    //商品操作记录
    //注入service操作
    @Autowired
    private StoreOperService storeOperService;

    @RequestMapping("/toStoreOper")
    public String toStoreOper(){
        return "/WEB-INF/jsp/storeoper.jsp";
    }

    @RequestMapping(value = "/add")
    @ResponseBody
    public MyResult add(Long odid, Long storeId/*, HttpSession session*/){
       /* Emp emp = (Emp) session.getAttribute("loginUser");*/
        Emp emp = (Emp) SecurityUtils.getSubject().getPrincipal();
        if(emp == null){
            return  new MyResult(false,"你还没有登录");
        }
        try{
            storeOperService.addAndUpdate(emp.getEid(),odid,storeId);
            return new MyResult(true,"修改成功");
        }catch (Exception e){
            e.printStackTrace();
            return  new MyResult(false,"修改失败");
        }
    }


    //销售出库的操作
    @RequestMapping("/out")
    @ResponseBody
    public MyResult out(Long odid,Long storeId/*,HttpSession session*/){
        /*Emp emp = (Emp) session.getAttribute("loginUser");*/
        Emp emp = (Emp) SecurityUtils.getSubject().getPrincipal();
        if(emp == null){
            return new MyResult(false,"请你先登录");
        }
        MyResult myResult =  storeOperService.out(odid,storeId,emp.getEid());
        return myResult;
    }

    //获取全部信息
    @RequestMapping("/findAll")
    @ResponseBody
    public String findAll(StoreOper storeOper,Integer page,Integer rows){
        List<StoreOper> storeOperList = storeOperService.findAll(storeOper,page,rows);
        //获取总个数
        Integer count =  storeOperService.selectCountAll();
        EasyResult<StoreOper> easyResult = new EasyResult<StoreOper>();
        easyResult.setRows(storeOperList);
        easyResult.setTotal(count);
        return JSON.toJSONString(easyResult);
    }


}
