package com.erp.service;

import java.util.List;
import java.util.Map;

/**
 * 统计服务接口
 *
 * 提供统计报表的核心业务功能，包括：
 * - 订单统计报表
 * - 年度销售额统计
 *
 * @author baoyang System
 * @version 1.0
 */
public interface CountService {

    /**
 * 获取订单统计报表
 *
     * @return 统计数据列表
 */
    List<Map<String, Object>> getOrderReport();

    /**
 * 获取年度月度销售额统计
 *
     * @param years 年份
     * @return 销售额统计数据
 */
    List<Map<String, Object>> getSumMoney(Integer years);
}