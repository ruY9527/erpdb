package com.erp.controller;

import com.alibaba.fastjson.JSON;
import com.erp.service.StoreService;
import com.erp.utils.MyResult;
import com.yang.erp.pojo.Emp;
import com.yang.erp.pojo.Store;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/store")
public class StoreController {

    @Autowired
    private StoreService storeService;

    //跳转页面
    @RequestMapping("/toStore")
    public String toStore(){
        return "/WEB-INF/jsp/store.jsp";
    }

    //获取store中信息
    @RequestMapping("/findAll")
    @ResponseBody
    public String findAll(Store store,Integer page,Integer rows){
        List<Store> storeList = storeService.findAll(store,page,rows);
        return JSON.toJSONString(storeList);
    }

    //添加
    @RequestMapping("/add")
    @ResponseBody
    public MyResult add(Store store){
        try
        {
            storeService.add(store);
            return new MyResult(true,"添加成功");
        }catch (Exception e){
            return new MyResult(false,"添加失败");
        }
    }

    //删除(虚拟删除,根据sid修改state为0)
    @RequestMapping("/delete")
    @ResponseBody
    public MyResult delete(@RequestParam("id") Long sid){
        try
        {
            storeService.delete(sid);
            return new MyResult(true,"删除成功");
        }catch (Exception e){
            return new MyResult(false,"删除失败");
        }
    }

    //根据sid获取pojo对象便于修改
    @RequestMapping("/getPojoById")
    @ResponseBody
    public String getPojoById(@RequestParam("id") Long sid){
        Store store =  storeService.getPojoById(sid);
        return JSON.toJSONString(store);
    }

    //修改操作
    @RequestMapping("/update")
    @ResponseBody
    public MyResult update(Store store){
        try{
            storeService.update(store);
            return new MyResult(true,"修改成功");
        }catch (Exception e){
            e.printStackTrace();
            return new MyResult(false,"修改失败");
        }
    }

    //只显示当前登录的store信息
    @RequestMapping("/findByLoginEmp")
    @ResponseBody
    public String findByLoginEmp(/*HttpSession session*/){
        /*Emp emp = (Emp) session.getAttribute("loginUser");*/
        Emp emp = (Emp) SecurityUtils.getSubject().getPrincipal();
        if(emp == null){
            return "请你先登录";
        }
        List<Store> storeList = storeService.findByEmpId(emp.getEid());
        return JSON.toJSONString(storeList);
    }

    //获取全部信息
    @RequestMapping("selectAll")
    @ResponseBody
    public String selectAll(){
        List<Store> storeList =  storeService.selectAll();
        return JSON.toJSONString(storeList);
    }

    //导出
    @RequestMapping("/export")
    @ResponseBody
    public MyResult export(Store store, HttpServletResponse response){
        String filename = "仓库.xls";
        try
        {
            response.setHeader("Content-Disposition","attachment;filename="+new String(filename.getBytes(),"ISO-8859-1"));
            storeService.export(store,response.getOutputStream());
            return new MyResult(true,"导出成功");
        }catch (Exception e){
            e.printStackTrace();
            return new MyResult(false,"导出失败");
        }
    }

    //导入操作
    @RequestMapping("/doImport")
    @ResponseBody
    public MyResult doImport(@RequestParam("file") MultipartFile multipartFile){
        return new MyResult(false,"仓库不支持导入");
    }
}
