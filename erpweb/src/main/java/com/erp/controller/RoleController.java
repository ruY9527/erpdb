package com.erp.controller;

import com.alibaba.fastjson.JSON;
import com.erp.service.RoleService;
import com.erp.utils.MyResult;
import com.yang.erp.pojo.EasyResult;
import com.yang.erp.pojo.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController
{

    @Autowired
    private RoleService roleService;

    @RequestMapping("/toRole")
    public String toRole(){
        return "/WEB-INF/jsp/role.jsp";
    }

    //获取(根据条件或者全部)
    @RequestMapping("/findAll")
    @ResponseBody
    public String findAll(Role role,Integer page,Integer rows){
        List<Role> roleList =  roleService.findAll(role,page,rows);
        EasyResult<Role> easyResult = new EasyResult<Role>();
        easyResult.setRows(roleList);
        Integer count =  roleService.selectCount();
        easyResult.setTotal(count);
        return JSON.toJSONString(easyResult);
    }

    //添加
    @RequestMapping("/add")
    @ResponseBody
    public MyResult add(Role role){
        try
        {
            roleService.add(role);
            return new MyResult(true,"添加成功");
        }catch (Exception e){
            e.printStackTrace();
            return new MyResult(false,"添加失败");
        }
    }

    //根据id获取对象信息
    @RequestMapping("/getPojoById")
    @ResponseBody
    public String getPojoById(@RequestParam("id") Long rid){
        Role role =  roleService.getPojoById(rid);
        return JSON.toJSONString(role);
    }

    //修改
    @RequestMapping("/update")
    @ResponseBody
    public MyResult update(Role role){
        try{
            roleService.update(role);
            return new MyResult(true,"修改成功");
        }catch (Exception e){
            e.printStackTrace();
            return new MyResult(false,"修改失败");
        }
    }

    //虚拟删除
    @RequestMapping("delete")
    @ResponseBody
    public MyResult delete(@RequestParam("id") Long rid){
        try{
            roleService.delete(rid);
            return new MyResult(true,"删除成功");
        }catch (Exception e){
            e.printStackTrace();
            return new MyResult(false,"删除失败");
        }
    }

    @RequestMapping("/select")
    @ResponseBody
    public String select(){
        List<Role> roles =  roleService.selectAll();
        return JSON.toJSONString(roles);
    }
}
