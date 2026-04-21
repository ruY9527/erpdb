package com.erp.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.erp.pojo.ReturnOrders;

import java.util.List;

/**
 * 退货订单服务接口
 *
 * @author baoyang
 */
public interface ReturnOrdersService {

    List<ReturnOrders> list(String type, String state);

    Page<ReturnOrders> page(Integer page, Integer rows, String type, String state, Long supplierId);

    void add(ReturnOrders returnOrders);

    void check(Long id, Long checker);

    void out(Long id, Long ender);

    ReturnOrders getById(Long id);

    void delete(Long id);
}