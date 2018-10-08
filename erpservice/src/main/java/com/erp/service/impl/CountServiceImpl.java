package com.erp.service.impl;

import com.erp.mapper.CountMapper;
import com.erp.service.CountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CountServiceImpl implements CountService
{
    @Autowired
    private CountMapper countMapper;

    //获取name，y信息
    @Override
    public List orderReport(String startDate,String endDate)
    {
        return countMapper.orderReport(startDate,endDate);
    }

    //根据年获取每月的变化
    @Override
    public List<Map<String,Object>> getSumMoney(Integer years)
    {
        //保存每月的销额
        List<Map<String,Object>> result = new ArrayList<Map<String,Object>>(12);
        //当年每月的销售
        List<Map<String, Object>> sumMoney = countMapper.getSumMoney(years);
        //转化为每月的销售
        Map<String,Map<String,Object>> map = new HashMap<String,Map<String,Object>>();
        for(Map<String,Object> m : sumMoney){
            map.put(m.get("month")+"月",m);
        }
        Map<String,Object> data = null;
        for(int i=1;i<=12;i++){
            data = map.get(i+"月");
            if(data == null){
                //当月没销售额
                data = new HashMap<String,Object>();
                data.put("month",i+"月");
                data.put("y",0);
            }
            result.add(data);
        }
        return result;
    }
}
