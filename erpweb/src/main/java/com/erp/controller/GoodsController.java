package com.erp.controller;

import com.alibaba.fastjson.JSON;
import com.erp.annotation.SystemControllerLog;
import com.erp.service.GoodsService;
import com.erp.utils.MyResult;
import com.yang.erp.pojo.EasyResult;
import com.yang.erp.pojo.Goods;
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
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    //去orders_add的页面跳转
    @RequestMapping("/toAdd")
    public String toAdd(){
        return "/WEB-INF/jsp/goods.jsp";
    }

    @RequestMapping("/findAll")
    @ResponseBody
    @ApiOperation(value = "商品信息")
    @SystemControllerLog(description = "商品信息")
    public String findAll(Goods goods,Integer page,Integer rows){
        //分页查询完结果
        List<Goods> goodsList = goodsService.findAll(goods,page,rows);
        //获取个数
        Integer countNum =  goodsService.getCount();
        EasyResult<Goods> easyResult = new EasyResult<Goods>();
        easyResult.setTotal(countNum);
        easyResult.setRows(goodsList);
        return JSON.toJSONString(easyResult);
    }

    @RequestMapping("/add")
    @ResponseBody
    @ApiOperation(value = "添加商品信息")
    @SystemControllerLog(description = "添加商品信息")
    public MyResult add(Goods goods){
        try{
            goodsService.add(goods);
            return new MyResult(true,"添加成功");
        }catch (Exception e){
            e.printStackTrace();
            return new MyResult(false,"添加失败");
        }
    }

    //根据id获取一个对象,便于修改
    @RequestMapping("/getPojoById")
    @ResponseBody
    public String getPojoById(@RequestParam("id") Long gsid){
        Goods goods = goodsService.getPojoById(gsid);
        return JSON.toJSONString(goods);
    }

    //修改操作
    @RequestMapping("/update")
    @ResponseBody
    @ApiOperation(value = "修改商品信息")
    @SystemControllerLog(description = "修改商品信息")
    public MyResult update(Goods goods){
        try{
            goodsService.update(goods);
            return new MyResult(true,"修改成功");
        }catch (Exception e){
            e.printStackTrace();
            return new MyResult(false,"修改失败");
        }

    }

    //删除虚拟修改操作
    @RequestMapping("/delete")
    @ResponseBody
    @ApiOperation(value = "删除商品信息")
    @SystemControllerLog(description = "删除商品信息")
    public MyResult delete(@RequestParam("id") Long gsid){
        try{
            goodsService.delete(gsid);
            return new MyResult(true,"删除成功");
        }catch (Exception e){
            return new MyResult(false,"添加失败");
        }

    }

    //查询所有已经入库的商品，才可以进行退货
    @RequestMapping("/findAllBySalesReturn")
    @ResponseBody
    public String findAllBySalesReturn(String msg){
        List<Goods> goodsList =  goodsService.findAllBySalesReturn(msg);
        return JSON.toJSONString(goodsList);
    }

    //导出商品类型
    @RequestMapping("/export")
    @ResponseBody
    @ApiOperation(value = "导出商品信息")
    @SystemControllerLog(description = "导出商品信息")
    public MyResult export(Goods goods, HttpServletResponse response){
        String filename = "商品管理.xls";
        try{
            //中文转换
            response.setHeader("Content-Disposition","attachment;filename="+new String(filename.getBytes(),"ISO-8859-1"));
            //supplierService.export(supplier,response.getOutputStream());
            goodsService.export(goods,response.getOutputStream());
            return new MyResult(true,"导出成功");
        }catch (Exception e){
            e.printStackTrace();
            return new MyResult(false,"导出失败");
        }
    }

    //导入的操作
    @RequestMapping("/doImport")
    @ResponseBody
    @ApiOperation(value = "导入商品信息")
    @SystemControllerLog(description = "导入商品信息")
    public MyResult doImport(@RequestParam("file") MultipartFile multipartFile){
        String contentType = multipartFile.getContentType();
        //String name = multipartFile.getOriginalFilename();
        if(!"application/vnd.ms-excel".equals(contentType)){
            return new MyResult(false,"上传的文件必须是excel文件");
        }
        try{
            MyResult myResult =  goodsService.doImport(multipartFile.getInputStream());
            return myResult;
        }catch (Exception e){
            e.printStackTrace();
            return new MyResult(false,"上传文件失败") ;
        }
    }

}
