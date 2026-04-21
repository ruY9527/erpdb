package com.erp.service.impl;

import com.erp.mapper.WaybilldetailMapper;
import com.erp.service.WaybilldetailService;
import com.erp.pojo.WayBillDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 运单详情服务实现类
 *
 * 实现运单详情管理的核心业务逻辑，包括：
 * - 运单详情查询
 *
 * @author baoyang System
 * @version 1.0
 */
@Service
public class WaybilldetailServiceImpl implements WaybilldetailService {

    @Autowired
    private WaybilldetailMapper waybilldetailMapper;

    /**
 * 根据运单号查询运单详情
 *
     * @param waybillsn 运单号
     * @return 运单详情列表
 */
    @Override
    public List<WayBillDetail> findAll(String waybillsn) {
        return waybilldetailMapper.findByWaybillsn(waybillsn);
    }
}