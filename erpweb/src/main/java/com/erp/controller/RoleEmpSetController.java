package com.erp.controller;

import com.alibaba.fastjson.JSON;
import com.erp.service.RoleEmpSetService;
import com.erp.utils.MyResult;
import com.yang.erp.pojo.Tree;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/roleEmpSet")
public class RoleEmpSetController
{
    @Autowired
    private RoleEmpSetService roleEmpSetService;

    //去往页面
    @RequestMapping("/toRoleEmpSet")
    public String toRoleEmpSet(){
        return "/WEB-INF/jsp/roleEmp.jsp";
    }

    //获取全部信息
    @RequestMapping("/findAll")
    @ResponseBody
    public String findAll(@Param("eid") Long eid){
        List<Tree> treeList =  roleEmpSetService.findAll(eid);
        return JSON.toJSONString(treeList);
    }

    //添加操作
    @RequestMapping("/updateRoleEmp")
    @ResponseBody
    public MyResult updateRoleEmp(Long eid,String checkedStr){
        try {
            roleEmpSetService.updateRoleEmp(eid,checkedStr);
            return new MyResult(true,"成功");
        }catch (Exception e){
            e.printStackTrace();
            return new MyResult(false,"失败");
        }
    }

}
