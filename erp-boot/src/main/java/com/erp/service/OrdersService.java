package com.erp.service;

import com.erp.pojo.Orders;

import java.util.List;

/**
 * 订单服务接口
 *
 * 提供订单管理的核心业务功能，包括：
 * - 订单查询（采购/销售）
 * - 订单状态流转（审核、确认、入库）
 *
 * @author baoyang System
 * @version 1.0
 */
public interface OrdersService {

    /**
 * 根据类型和状态查询订单
 *
     * @param type 订单类型：1-采购订单，2-销售订单
     * @param state 订单状态
     * @return 订单列表
 */
    List<Orders> findByType(String type, String state);

    /**
 * 更新订单状态
 *
     * @param eid 操作员工ID
     * @param oid 订单ID
 */
    void updateStateByOid(Long eid, Long oid);

    /**
 * 查询员工相关的订单
 *
     * @param eid 员工ID
     * @param type 订单类型
     * @return 订单列表
 */
    List<Orders> myFindByType(Long eid, String type);

    /**
 * 订单确认入库/出库
 *
     * @param eid 操作员工ID
     * @param oid 订单ID
 */
    void doStart(Long eid, Long oid);
}