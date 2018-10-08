package com.erp.controller;

import com.alibaba.fastjson.JSON;
import com.erp.service.LogService;
import com.yang.erp.pojo.EasyResult;
import com.yang.erp.pojo.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/myLog")
public class LogController
{

    @Autowired
    private LogService logService;

    //去往日志记录页面
    @RequestMapping("/toLog")
    public String toLog(){
        return "/WEB-INF/jsp/log.jsp";
    }

    //查询全部(或者根据条件查询)
    @RequestMapping("/findAll")
    @ResponseBody
    public String findAll(Log log,Integer page,Integer rows){
        List<Log> logList =  logService.findAll(log,page,rows);
        //获取个数
        Integer count =  logService.selectCount();
        EasyResult<Log> logEasyResult = new EasyResult<>();
        logEasyResult.setTotal(count);
        logEasyResult.setRows(logList);
        return JSON.toJSONString(logEasyResult);
    }

}
