package com.erp.controller;

import com.erp.annotation.SystemControllerLog;
import com.erp.json.SecuryPwd;
import com.erp.service.EmpService;
import com.erp.utils.MyResult;
import com.yang.erp.pojo.Emp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/login")
public class LoginController
{
    @Autowired
    private EmpService empService;


    //根据username和pwd来获取信息
    @RequestMapping("/login")
    @ResponseBody
    @ApiOperation(value = "登入系统",notes = "登入系统")
    @SystemControllerLog(description = "登入系统")
    public MyResult login(String username, String pwd/*, HttpSession session*/){
      /*  Emp emp = empService.findEmpByUsernameAndPwd(username, pwd);
        if(emp !=null && !emp.equals("")){
            session.setAttribute("loginUser",emp);
            return new MyResult(true,"登录成功");
        }
        return new MyResult(false,"登陆失败");*/
        //对密码进行盐值加密
        pwd =  pwd = SecuryPwd.encrey(pwd,username);
        try{
            //创建令牌
            UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username,pwd);
            org.apache.shiro.subject.Subject subject = SecurityUtils.getSubject();
            try
            {
                subject.login(usernamePasswordToken);
            }catch (UnknownAccountException e){
                e.printStackTrace();
                return new MyResult(false,"登陆失败");
            }
            return new MyResult(true,"");
        } catch (Exception e){
            e.printStackTrace();
            return new MyResult(false,"登录失败");
        }
    }


    //根据session来获取信息
    @RequestMapping("/showName")
    @ResponseBody
    @ApiOperation(value = "获取用户名")
    @SystemControllerLog(description = "获取用户名")
    public MyResult showName(/*HttpSession session*/){
        //Emp emp = (Emp) session.getAttribute("loginUser");
        Emp emp = (Emp) SecurityUtils.getSubject().getPrincipal();
        if(emp !=null){
            return new MyResult(true,emp.getUsername());
        } else {
            return new MyResult(false,"你还没登录");
        }
    }

    //退出登录
    @RequestMapping("/loginOut")
    @ResponseBody
    @ApiOperation(value = "退出系统")
    @SystemControllerLog(description = "退出系统")
    public MyResult loginOut(/*HttpSession session*/){
        try{
            /*session.invalidate();
            return new MyResult(true,"退出成功");*/
            SecurityUtils.getSubject().logout();
            return new MyResult(true,"退出成功");
        } catch (Exception e){
            e.printStackTrace();
            return  new MyResult(false,"退出失败");
        }
    }
}
