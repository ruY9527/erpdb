package com.erp.service;

import com.erp.pojo.WayBillDetail;

import java.util.List;

/**
 * 运单详情服务接口
 *
 * 提供运单详情管理的核心业务功能，包括：
 * - 运单详情查询
 *
 * @author baoyang System
 * @version 1.0
 */
public interface WaybilldetailService {

    /**
 * 根据运单号查询运单详情
 *
     * @param waybillsn 运单号
     * @return 运单详情列表
 */
    List<WayBillDetail> findAll(String waybillsn);
}