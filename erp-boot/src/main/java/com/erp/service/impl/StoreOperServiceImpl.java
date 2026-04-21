package com.erp.service.impl;

import com.erp.mapper.StoreoperMapper;
import com.erp.service.StoreOperService;
import com.github.pagehelper.PageHelper;
import com.erp.pojo.StoreOper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 仓库操作服务实现类
 *
 * 实现仓库操作记录管理的核心业务逻辑，包括：
 * - 仓库操作记录查询
 * - 入库/出库操作记录
 *
 * @author baoyang System
 * @version 1.0
 */
@Service
public class StoreOperServiceImpl implements StoreOperService {

    @Autowired
    private StoreoperMapper storeoperMapper;

    /**
 * 查询仓库操作记录（支持分页）
     * 可按仓库、商品、操作类型等条件筛选
 *
     * @param storeOper 查询条件
     * @param page 页码
     * @param rows 每页行数
     * @return 操作记录列表
 */
    @Override
    public List<StoreOper> findAll(StoreOper storeOper, Integer page, Integer rows) {
        PageHelper.startPage(page, rows);
        List<StoreOper> storeOperList = storeoperMapper.findAll(storeOper);
        return storeOperList;
    }

    /**
 * 新增仓库操作记录
     * 记录入库或出库操作信息
 *
     * @param storeOper 操作记录信息
 */
    @Override
    public void add(StoreOper storeOper) {
        storeoperMapper.add(storeOper);
    }

    /**
 * 获取操作记录总数
 *
     * @return 记录数量
 */
    @Override
    public Integer getCount() {
        return storeoperMapper.selectCountAll();
    }
}