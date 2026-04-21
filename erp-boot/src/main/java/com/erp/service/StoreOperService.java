package com.erp.service;

import com.erp.pojo.StoreOper;

import java.util.List;

/**
 * 仓库操作服务接口
 *
 * 提供仓库操作记录管理的核心业务功能，包括：
 * - 仓库操作记录查询
 * - 入库/出库操作
 *
 * @author baoyang System
 * @version 1.0
 */
public interface StoreOperService {

    /**
 * 查询仓库操作记录（支持分页）
 *
     * @param storeOper 查询条件
     * @param page 页码
     * @param rows 每页行数
     * @return 操作记录列表
 */
    List<StoreOper> findAll(StoreOper storeOper, Integer page, Integer rows);

    /**
 * 新增仓库操作记录
 *
     * @param storeOper 操作记录信息
 */
    void add(StoreOper storeOper);

    /**
 * 获取操作记录总数
 *
     * @return 记录数量
 */
    Integer getCount();
}