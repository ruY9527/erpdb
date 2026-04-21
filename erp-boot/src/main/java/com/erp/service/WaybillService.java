package com.erp.service;

import com.erp.pojo.WayBill;

import java.util.List;

/**
 * 运单服务接口
 *
 * 提供运单管理的核心业务功能，包括：
 * - 运单查询
 * - 运单状态更新
 *
 * @author baoyang System
 * @version 1.0
 */
public interface WaybillService {

    /**
 * 根据订单ID查询运单
 *
     * @param ordersid 订单ID
     * @return 运单信息
 */
    WayBill findByOrdersId(Long ordersid);

    /**
 * 查询所有运单
 *
     * @return 运单列表
 */
    List<WayBill> findAll();
}