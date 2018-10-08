package com.erp.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.erp.annotation.SystemControllerLog;
import com.erp.json.SecuryPwd;
import com.erp.service.EmpService;
import com.erp.utils.MyResult;
import com.yang.erp.pojo.EasyResult;
import com.yang.erp.pojo.Emp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.security.auth.Subject;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/emp")
public class EmpController {

    @Autowired
    private EmpService empService;




    @RequestMapping("/toEmp")
    public String toEmp(){
        return "/WEB-INF/jsp/emp.jsp";
    }

    //获取全部信息的方法
    @RequestMapping("/findAll")
    @ResponseBody
    @ApiOperation(value = "员工信息")
    @SystemControllerLog(description = "员工信息")
    public String findAll(Emp emp,Integer page,Integer rows){
        List<Emp> empList = empService.selectByEmp(emp,page,rows);
        //获取总个数
        Integer count = empService.getCount();
        EasyResult<Emp> easyResult = new EasyResult<Emp>();
        easyResult.setTotal(count);
        easyResult.setRows(empList);
        return JSON.toJSONString(easyResult);
    }

    //添加方法
    @RequestMapping("/add")
    @ResponseBody
    @ApiOperation(value = "添加员工信息")
    @SystemControllerLog(description = "添加员工信息")
    public MyResult addEmp(Emp emp){
        try{
            String pwd = SecuryPwd.encrey(emp.getUsername(), emp.getUsername());
            emp.setPwd(pwd);
            empService.addEmp(emp);
            return new MyResult(true,"添加成功，默认密码就是你的用户名");
        }catch (Exception e){
            e.printStackTrace();
            return new MyResult(false,"添加失败");
        }
    }

    //根据id删除信息的方法
    @RequestMapping("/delete")
    @ResponseBody
    @ApiOperation(value = "删除信息")
    @SystemControllerLog(description = "删除信息")
    public MyResult deleteEmp(Long id){
        try{
            empService.deleteEmp(id);
            return new MyResult(true,"添加成功");
        }catch (Exception e){
            e.printStackTrace();
            return new MyResult(false,"删除失败");
        }
    }

    //根据一个id获取一个对象信息
    @ResponseBody
    @RequestMapping("/getPojoById")
    public String getPojoById(Long id){
        Emp empById = empService.getEmpById(id);
        return JSON.toJSONString(empById);
    }


    //前端传递新旧密码来做修改
    @RequestMapping("/updatePwd")
    @ResponseBody
    @ApiOperation(value = "修改员工信息")
    @SystemControllerLog(description = "修改员工信息")
    public MyResult updatePwd(String oldPwd,String newPwd,HttpSession session){
       /* Emp emp = (Emp) session.getAttribute("loginUser");*/
       Emp emp = (Emp) SecurityUtils.getSubject().getPrincipal();
        if(emp == null){
            return new MyResult(false,"你还没登录");
        }
        //根据eid和oldPwd查询信息
        //久密码加密
        oldPwd = SecuryPwd.encrey(oldPwd,emp.getUsername());
        Emp oldEmp = empService.checkEmpByEidAndPwd(emp.getEid(), oldPwd);
        if(oldEmp == null){
            return new MyResult(false,"你输入的旧密码错误");
        }
        try{
            //新密码加密
            newPwd =  SecuryPwd.encrey(newPwd,emp.getUsername());
            empService.updatePwdByEid(emp.getEid(),newPwd);
            return new MyResult(true,"修改成功");
        }catch (Exception e){
            e.printStackTrace();
            return new MyResult(false,"修改失败");
        }
    }


    //管理员方式修改密码
    @RequestMapping("/adminUpdatePwd")
    @ResponseBody
    @ApiOperation(value = "管理员修改密码")
    @SystemControllerLog(description = "管理员修改密码")
    public MyResult adminUpdatePwd(Long eid,String pwd){
        Emp emp = empService.getEmpById(eid);
        try{
            //密码加盐
            pwd = SecuryPwd.encrey(pwd,emp.getUsername());
            empService.updatePwdByEid(eid,pwd);
            return new MyResult(true,"修改成功");
        }catch (Exception e){
            e.printStackTrace();
            return new MyResult(false,"修改失败");
        }
    }

    @RequestMapping("/selectAll")
    @ResponseBody
    public String selectAll(){
        List<Emp> empList =  empService.selectAll();
        return JSON.toJSONString(empList, SerializerFeature.DisableCircularReferenceDetect,SerializerFeature.WriteMapNullValue);
    }

    @RequestMapping("/update")
    @ResponseBody
    public MyResult update(Emp emp){
        try{
            empService.updateEmp(emp);
            return new MyResult(true,"修改成功");
        }catch (Exception e){
            e.printStackTrace();
            return new MyResult(false,"修改失败");
        }
    }


    //导出
    @RequestMapping("/export")
    @ResponseBody
    @ApiOperation(value = "员工导出")
    @SystemControllerLog(description = "员工导出")
    public MyResult export(Emp emp, HttpServletResponse response){
        String filename = "员工.xls";
        try
        {
            response.setHeader("Content-Disposition","attachment;filename="+new String(filename.getBytes(),"ISO-8859-1"));
            empService.export(emp,response.getOutputStream());
            return new MyResult(true,"导出成功");
        }catch (Exception e){
            e.printStackTrace();
            return new MyResult(false,"导出失败");
        }
    }


    //导入操作
    @RequestMapping("/doImport")
    @ResponseBody
    @ApiOperation(value = "员工导入")
    @SystemControllerLog(description = "员工导入")
    public MyResult doImport(@RequestParam("file") MultipartFile multipartFile){
        String contentType = multipartFile.getContentType();
        if(!"application/vnd.ms-excel".equals(contentType)){
            return new MyResult(false,"上传的文件必须是excel文件");
        }
        try{
            MyResult myResult = empService.doImport(multipartFile.getInputStream());
            return myResult;
        }catch (Exception e){
            e.printStackTrace();
            return new MyResult(false,"上传文件失败") ;
        }
    }


}
