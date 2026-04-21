package com.erp.service.impl;

import com.erp.mapper.CountMapper;
import com.erp.service.CountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 统计服务实现类
 *
 * 实现统计报表的核心业务逻辑，包括：
 * - 订单统计报表
 * - 年度销售额统计
 *
 * @author baoyang System
 * @version 1.0
 */
@Service
public class CountServiceImpl implements CountService {

    @Autowired
    private CountMapper countMapper;

    /**
 * 获取订单统计报表
     * 统计指定时间范围内的订单数量和金额
 *
     * @return 统计数据列表
 */
    @Override
    public List<Map<String, Object>> getOrderReport() {
        return countMapper.orderReport(null, null);
    }

    /**
 * 获取年度月度销售额统计
     * 返回指定年份每月的销售额数据
 *
     * @param years 年份
     * @return 销售额统计数据（12个月的数据）
 */
    @Override
    public List<Map<String, Object>> getSumMoney(Integer years) {
        List<Map<String, Object>> result = new ArrayList<>(12);
        List<Map<String, Object>> sumMoney = countMapper.getSumMoney(years);
        
        // 转化为每月的销售数据
        Map<String, Map<String, Object>> map = new HashMap<>();
        for (Map<String, Object> m : sumMoney) {
            map.put(m.get("month") + "月", m);
        }
        
        // 补齐12个月的数据，没有销售额的月份设为0
        Map<String, Object> data = null;
        for (int i = 1; i <= 12; i++) {
            data = map.get(i + "月");
            if (data == null) {
                data = new HashMap<>();
                data.put("month", i + "月");
                data.put("y", 0);
            }
            result.add(data);
        }
        return result;
    }
}