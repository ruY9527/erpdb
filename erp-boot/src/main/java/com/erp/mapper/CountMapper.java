package com.erp.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 统计报表 Mapper 接口
 *
 * @author baoyang
 */
public interface CountMapper {

    List orderReport(@Param("startDate") String startDate, @Param("endDate") String endDate);

    List<Map<String, Object>> getSumMoney(@Param("years") Integer years);
}