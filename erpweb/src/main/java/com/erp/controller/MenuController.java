package com.erp.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.erp.service.MenuService;
import com.yang.erp.pojo.Emp;
import com.yang.erp.pojo.Menu;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @RequestMapping(value = "/queryMenuTree",produces = "text/json;charset=utf-8")
    @ResponseBody
    public String queryMenuTree(@RequestParam String pid){
        Menu menus = menuService.queryTreeList(pid);
        return JSON.toJSONString(menus);
    }

    @RequestMapping(value = "/queryMenuTreeBtEmpId",produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String queryMenuTreeBtEmpId(HttpSession session){
       /* Emp emp = (Emp) session.getAttribute("loginUser");*/
        Emp emp = (Emp) SecurityUtils.getSubject().getPrincipal();
        if(emp == null){
            return "请你先登录";
        }
        Menu menu =  menuService.queryMenuTreeBtEmpId(emp.getEid());
        return JSON.toJSONString(menu, SerializerFeature.WriteMapNullValue,SerializerFeature.DisableCircularReferenceDetect);
    }

}
