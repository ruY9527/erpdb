package com.erp.service;

import com.erp.pojo.Ordersdetail;

import java.util.List;

/**
 * 订单详情服务接口
 *
 * 提供订单详情管理的核心业务功能，包括：
 * - 订单详情查询
 * - 订单详情增删改
 *
 * @author baoyang System
 * @version 1.0
 */
public interface OrdersdetailService {

    /**
 * 根据订单ID查询订单详情
 *
     * @param ordersid 订单ID
     * @return 订单详情列表
 */
    List<Ordersdetail> findAll(Long ordersid);

    /**
 * 新增订单详情
 *
     * @param ordersdetail 订单详情信息
 */
    void add(Ordersdetail ordersdetail);

    /**
 * 根据ID删除订单详情
 *
     * @param uuid 订单详情ID
 */
    void delete(Long uuid);

    /**
 * 更新订单详情
 *
     * @param ordersdetail 订单详情信息
 */
    void update(Ordersdetail ordersdetail);
}