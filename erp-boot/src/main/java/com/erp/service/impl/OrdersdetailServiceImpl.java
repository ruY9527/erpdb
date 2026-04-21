package com.erp.service.impl;

import com.erp.mapper.OrdersdetailMapper;
import com.erp.service.OrdersdetailService;
import com.erp.pojo.Ordersdetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 订单详情服务实现类
 *
 * 实现订单详情管理的核心业务逻辑，包括：
 * - 订单详情查询
 * - 订单详情增删改
 *
 * @author baoyang System
 * @version 1.0
 */
@Service
public class OrdersdetailServiceImpl implements OrdersdetailService {

    @Autowired
    private OrdersdetailMapper ordersdetailMapper;

    /**
 * 根据订单ID查询订单详情
 *
     * @param ordersid 订单ID
     * @return 订单详情列表
 */
    @Override
    public List<Ordersdetail> findAll(Long ordersid) {
        Ordersdetail detail = ordersdetailMapper.findByOid(ordersid);
        List<Ordersdetail> list = new ArrayList<>();
        if (detail != null) {
            list.add(detail);
        }
        return list;
    }

    /**
 * 新增订单详情
 *
     * @param ordersdetail 订单详情信息
 */
    @Override
    public void add(Ordersdetail ordersdetail) {
        ordersdetailMapper.add(ordersdetail);
    }

    /**
 * 根据ID删除订单详情
 *
     * @param uuid 订单详情ID
 */
    @Override
    public void delete(Long uuid) {
        ordersdetailMapper.delete(uuid);
    }

    /**
 * 更新订单详情
 *
     * @param ordersdetail 订单详情信息
 */
    @Override
    public void update(Ordersdetail ordersdetail) {
        ordersdetailMapper.update(ordersdetail);
    }
}