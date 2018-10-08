package com.erp.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CountMapper
{
    //@Param("startDate") String startDate,@Param("endDate") String endDate
    public List orderReport(@Param("startDate") String startDate,@Param("endDate") String endDate);

    //根据年获取当年每月的报表操作
    public List<Map<String,Object>> getSumMoney(@Param("years") Integer years);

}
