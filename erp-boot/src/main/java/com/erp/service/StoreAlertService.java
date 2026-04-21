package com.erp.service;

import com.erp.pojo.StoreAlert;

import java.util.List;

/**
 * 库存预警服务接口
 *
 * 提供库存预警管理的核心业务功能，包括：
 * - 库存预警查询
 * - 预警邮件发送
 *
 * @author baoyang System
 * @version 1.0
 */
public interface StoreAlertService {

    /**
 * 查询所有库存预警信息
 *
     * @return 预警列表
 */
    List<StoreAlert> findAll();

    /**
 * 发送库存预警邮件
 */
    void sendEmail();
}