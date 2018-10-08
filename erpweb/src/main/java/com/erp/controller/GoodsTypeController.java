package com.erp.controller;

import com.alibaba.fastjson.JSON;
import com.erp.annotation.SystemControllerLog;
import com.erp.service.GoodsService;
import com.erp.service.GoodsTypeService;
import com.erp.utils.MyResult;
import com.yang.erp.pojo.EasyResult;
import com.yang.erp.pojo.Goods;
import com.yang.erp.pojo.GoodsType;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/goodsType")
public class GoodsTypeController
{
    @Autowired
    private GoodsTypeService goodsTypeService;

    @RequestMapping("/toGoodsType")
    public String toGoodsType(){
        return "/WEB-INF/jsp/goodstype.jsp";
    }

    @RequestMapping("/findAll")
    @ResponseBody
    @ApiOperation(value = "商品类型信息")
    @SystemControllerLog(description = "商品类型信息")
    public String findAll(GoodsType goodsType,Integer page,Integer rows){
        //获取分页的结果
        List<GoodsType> goodsTypeList =  goodsTypeService.findAll(goodsType,page,rows);
        //获取有效的个数
        Integer countNum =  goodsTypeService.getCount();
        //分装返回给页面
        EasyResult<GoodsType> easyResult = new EasyResult<GoodsType>();
        easyResult.setRows(goodsTypeList);
        easyResult.setTotal(countNum);
        return JSON.toJSONString(easyResult);
    }

    @RequestMapping("/selectAll")
    @ResponseBody
    public String selectAll(){
        List<GoodsType> goodsTypeList = goodsTypeService.selectAll();
        return JSON.toJSONString(goodsTypeList);
    }

    @RequestMapping("/add")
    @ResponseBody
    @ApiOperation(value = "添加商品类型信息")
    @SystemControllerLog(description = "添加商品类型信息")
    public MyResult add(String name){
        try{
            goodsTypeService.add(name);
            return new MyResult(true,"添加成功");
        }catch (Exception e){
            return new MyResult(false,"添加失败");
        }
    }

    @RequestMapping("/delete")
    @ResponseBody
    public MyResult delete(@RequestParam("id") Long gid){
        try{
            goodsTypeService.delete(gid);
            return new MyResult(true,"删除成功");
        }catch (Exception e){
            return new MyResult(false,"删除失败");
        }
    }

    //根据id查询获取对象便于修改
    @RequestMapping("/getPojoById")
    @ResponseBody
    public String getPojoById(@RequestParam("id") Long gid){
        GoodsType goodsType =  goodsTypeService.getPojoById(gid);
        return JSON.toJSONString(goodsType);
    }

    @RequestMapping("/update")
    @ResponseBody
    public MyResult update(GoodsType goodsType){
        try
        {
            goodsTypeService.update(goodsType);
            return new MyResult(true,"修改成功");
        }catch (Exception e){
            e.printStackTrace();
            return new MyResult(false,"修改失败");
        }
    }

    //导出商品类型
    @RequestMapping("/export")
    @ResponseBody
    public MyResult export(GoodsType goodsType,HttpServletResponse response){
        String filename = "商品类型.xls";
        try{
            //中文转换
            response.setHeader("Content-Disposition","attachment;filename="+new String(filename.getBytes(),"ISO-8859-1"));
            //supplierService.export(supplier,response.getOutputStream());
            goodsTypeService.export(goodsType,response.getOutputStream());
            return new MyResult(true,"导出成功");
        }catch (Exception e){
            e.printStackTrace();
            return new MyResult(false,"导出失败");
        }
    }

    //上传的操作
    @RequestMapping("/doImport")
    @ResponseBody
    public MyResult doImport(@RequestParam("file") MultipartFile multipartFile){
        String contentType = multipartFile.getContentType();
        //String name = multipartFile.getOriginalFilename();
        if(!"application/vnd.ms-excel".equals(contentType)){
            return new MyResult(false,"上传的文件必须是excel文件");
        }
        try{
            MyResult myResult =  goodsTypeService.doImport(multipartFile.getInputStream());
            return myResult;
        }catch (Exception e){
            e.printStackTrace();
            return new MyResult(false,"上传文件失败") ;
        }
    }



}
