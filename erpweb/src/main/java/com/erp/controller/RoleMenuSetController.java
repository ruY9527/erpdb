package com.erp.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.erp.service.RoleMenuSetService;
import com.erp.utils.MyResult;
import com.yang.erp.pojo.Tree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/roleMenuSet")
public class RoleMenuSetController
{

    @Autowired
    private RoleMenuSetService roleMenuSetService;

    @RequestMapping("/toRoleMenuSet")
    public String toRoleMenuSet(){
        return "/WEB-INF/jsp/roleMenuSet.jsp";
    }

    //获取泉下权限的菜单
    @RequestMapping(value = "/findAll",produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String findAll(@RequestParam("rid") Long rid){
        List<Tree> treeList =  roleMenuSetService.findAll(rid);
        return JSON.toJSONString(treeList, SerializerFeature.WriteMapNullValue,SerializerFeature.DisableCircularReferenceDetect);
    }

    @RequestMapping(value = "/updateRoleMenus")
    @ResponseBody
    public MyResult updateRoleMenus(Long rid,String checkedStr){
        try{
            roleMenuSetService.updateRoleMenus(rid,checkedStr);
            return new MyResult(true,"成功");
        }catch (Exception e){
            e.printStackTrace();
            return new MyResult(false,"失败");
        }
    }
}
