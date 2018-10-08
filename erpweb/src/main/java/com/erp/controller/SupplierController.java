package com.erp.controller;

import com.alibaba.fastjson.JSON;
import com.erp.service.SupplierService;
import com.erp.utils.MyResult;
import com.yang.erp.pojo.EasyResult;
import com.yang.erp.pojo.Supplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

@Controller
@RequestMapping("/supplier")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    @RequestMapping("/toSupplier")
    public String toSupplier(){
        return "/WEB-INF/jsp/supplier.jsp";
    }

    @RequestMapping("/findByType")
    @ResponseBody
    public String list(String type){
        List<Supplier> supplierList = supplierService.findByType(type);
        return JSON.toJSONString(supplierList);
    }

    @RequestMapping(value = "/findAll",produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String findAll(String type,Supplier supplier,Integer page,Integer rows){
        supplier.setType(type);
        //获取总数对象
        List<Supplier> supplierList = supplierService.findBySupplier(supplier,page,rows);
        EasyResult<Supplier> easyResult = new EasyResult<Supplier>();
        easyResult.setRows(supplierList);
        //获取个数
        Integer integer = supplierService.selectCountByType(type);
        easyResult.setTotal(integer);
        //返回页面json
        return JSON.toJSONString(easyResult);
    }

    @RequestMapping("/add")
    @ResponseBody
    public MyResult add(Supplier supplier,String type){
        supplier.setType(type);
        try
        {
            supplierService.add(supplier);
            return new MyResult(true,"添加成功");
        }catch (Exception e){
            e.printStackTrace();
            return  new MyResult(false,"添加失败");
        }
    }

    @RequestMapping("/getPojoById")
    @ResponseBody
    public String getPojoById(@RequestParam("id") Long suid){
        Supplier supplier =  supplierService.getPojoById(suid);
        return JSON.toJSONString(supplier);
    }

    @RequestMapping("/update")
    @ResponseBody
    public MyResult update(Supplier supplier){
        try
        {
            supplierService.update(supplier);
            return new MyResult(true,"修改成功");
        }catch (Exception e){
            return new MyResult(false,"修改失败");
        }
    }

    @RequestMapping("/delete")
    @ResponseBody
    public MyResult delete(@RequestParam("id") Long suid){
        try{
            supplierService.delete(suid);
            return new MyResult(true,"删除成功");
        }catch (Exception e){
            e.printStackTrace();
            return new MyResult(false,"删除失败");
        }
    }

    //导出操作
    @RequestMapping("/export")
    @ResponseBody
    public MyResult export(Supplier supplier,String type,HttpServletResponse response){
        String filename = "";
        supplier.setType(type);
        if(supplier.getType().equals("1")){
            filename = "供应商.xls";
        }
        if(supplier.getType().equals("2")){
            filename = "客户.xls";
        }
        try
        {
            //中文转换
            response.setHeader("Content-Disposition","attachment;filename="+new String(filename.getBytes(),"ISO-8859-1"));
            supplierService.export(supplier,response.getOutputStream());
            return new MyResult(true,"导出成功");
        } catch (IOException e)
        {
            e.printStackTrace();
            return new MyResult(false,"导入失败");
        }
    }

    //导入操作
    @RequestMapping("/doImport")
    @ResponseBody
    public MyResult doImport(@RequestParam("file") MultipartFile multipartFile){
        String contentType = multipartFile.getContentType();
        if(!"application/vnd.ms-excel".equals(contentType)){
            return new MyResult(false,"上传的文件必须是excel文件");
        }
        try{
            MyResult myResult = supplierService.doImport(multipartFile.getInputStream());
            return myResult;
        }catch (Exception e){
            e.printStackTrace();
            return new MyResult(false,"上传文件失败") ;
        }
    }

}
