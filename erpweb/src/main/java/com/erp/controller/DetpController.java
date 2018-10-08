package com.erp.controller;


import com.alibaba.fastjson.JSON;
import com.erp.annotation.SystemControllerLog;
import com.erp.service.DeptService;
import com.erp.utils.MyResult;
import com.yang.erp.pojo.Dept;
import com.yang.erp.pojo.EasyResult;
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
@RequestMapping("/dept")
public class DetpController {

    @Autowired
    private DeptService deptService;

    @RequestMapping("/dept_list")
    public String toDeptHtml(){
        return "/WEB-INF/jsp/dept.jsp";
    }

    /**
     *  获取全部部门的信息
     */
    @ResponseBody
    @RequestMapping(value = "/findAll",produces = "text/json;charset=UTF-8")
    @ApiOperation(value = "部门信息")
    @SystemControllerLog(description = "部门信息")
    public String findAll(Dept dept,Integer page,Integer rows){
        //List<Dept> deptList = deptService.findAll();
        List<Dept> deptList = deptService.selectDeptByNameOrTele(dept,page,rows);
        Integer allToatl =  deptService.getTotal();
        EasyResult<Dept> easyResult = new EasyResult<Dept>();
        easyResult.setRows(deptList);
        easyResult.setTotal(allToatl);
        return JSON.toJSONString(easyResult);
    }

    /**
     *不带分页的查询全部信息
     */
    @RequestMapping(value = "selectAll",produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String selectAll(){
        List<Dept> deptList = deptService.findAll();
        return JSON.toJSONString(deptList);
    }



    /**
     * 根据输入的name和tele来查询
     */
    @ResponseBody
    @RequestMapping(value="/selectDeptByNameOrTele",produces = "text/json;charset=UTF-8")
    public String selectDeptByNameOrTele(Dept dept,Integer page,Integer rows){
        List<Dept> depts = deptService.selectDeptByNameOrTele(dept,page,rows);
        return JSON.toJSONString(depts);
    }

    @ResponseBody
    @RequestMapping("/add")
    @ApiOperation(value = "添加部门信息")
    @SystemControllerLog(description = "添加部门信息")
    public MyResult addDept(Dept dept){
        try {
            deptService.add(dept);
            return new MyResult(true,"添加成功");
        }catch (Exception e){
            e.printStackTrace();
            return new MyResult(false,"添加失败");
        }
    }
    //根据id删除信息
    @ResponseBody
    @RequestMapping("/delete")
    @ApiOperation(value = "删除部门信息")
    @SystemControllerLog(description = "删除部门信息")
    public MyResult deleteDeptById(Long id){
        try{
            deptService.deleteDeptById(id);
            return new MyResult(true,"删除成功");
        }catch (Exception e){
            e.printStackTrace();
            return new MyResult(false,"删除失败");
        }
    }

    //根据id获取一个对象,便于修改的操作。
    @ResponseBody
    @RequestMapping("/getPojoById")
    public String getDeptById(Long id){
        Dept dept = deptService.getDeptById(id);
        return JSON.toJSONString(dept);
    }

    @ResponseBody
    @RequestMapping("/update")
    @ApiOperation(value = "修改部门信息")
    @SystemControllerLog(description = "修改部门信息")
    public MyResult updateDept(Dept dept){
        try{
            deptService.updateDept(dept);
            return new MyResult(true,"修改成功");
        }catch (Exception e){
            e.printStackTrace();
            return new MyResult(false,"修改失败");
        }
    }

    //导出操作
    @RequestMapping("/export")
    @ResponseBody
    @ApiOperation(value = "导出部门信息")
    @SystemControllerLog(description = "导出部门信息")
    public MyResult export(Dept dept, HttpServletResponse response){
        String filename = "部门.xls";
        try
        {
            response.setHeader("Content-Disposition","attachment;filename="+new String(filename.getBytes(),"ISO-8859-1"));
            deptService.export(dept,response.getOutputStream());
            return new MyResult(true,"导出成功");
        }catch (Exception e){
            e.printStackTrace();
            return new MyResult(false,"导出失败");
        }
    }

    //导入操作
    @RequestMapping("/doImport")
    @ResponseBody
    @ApiOperation(value = "导入部门信息")
    @SystemControllerLog(description = "导入部门信息")
    public MyResult doImport(@RequestParam("file") MultipartFile multipartFile){
        String contentType = multipartFile.getContentType();
        if(!"application/vnd.ms-excel".equals(contentType)){
            return new MyResult(false,"上传的文件必须是excel文件");
        }
        try{
            MyResult myResult = deptService.doImport(multipartFile.getInputStream());

            return myResult;
        }catch (Exception e){
            e.printStackTrace();
            return new MyResult(false,"上传文件失败") ;
        }
    }

}
