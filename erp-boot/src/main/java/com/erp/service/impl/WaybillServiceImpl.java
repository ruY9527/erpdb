package com.erp.service.impl;

import com.erp.mapper.WaybillMapper;
import com.erp.service.WaybillService;
import com.erp.pojo.WayBill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 运单服务实现类
 *
 * 实现运单管理的核心业务逻辑，包括：
 * - 运单查询
 * - 运单状态更新
 *
 * @author baoyang System
 * @version 1.0
 */
@Service
public class WaybillServiceImpl implements WaybillService {

    @Autowired
    private WaybillMapper waybillMapper;

    /**
 * 根据订单ID查询运单
 *
     * @param ordersid 订单ID
     * @return 运单信息
 */
    @Override
    public WayBill findByOrdersId(Long ordersid) {
        return waybillMapper.findByOrdersId(ordersid);
    }

    /**
 * 查询所有运单
 *
     * @return 运单列表
 */
    @Override
    public List<WayBill> findAll() {
        return waybillMapper.findAll(null);
    }
}