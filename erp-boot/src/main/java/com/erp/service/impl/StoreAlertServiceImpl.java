package com.erp.service.impl;

import com.erp.mapper.StoreAlertMapper;
import com.erp.service.StoreAlertService;
import com.erp.utils.EmailUtils;
import com.erp.pojo.StoreAlert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 库存预警服务实现类
 *
 * 实现库存预警管理的核心业务逻辑，包括：
 * - 库存预警查询
 * - 预警邮件发送
 *
 * @author baoyang System
 * @version 1.0
 */
@Service
public class StoreAlertServiceImpl implements StoreAlertService {

    @Autowired
    private StoreAlertMapper storeAlertMapper;

    @Autowired
    private EmailUtils emailUtils;

    /**
 * 查询所有库存预警信息
     * 返回库存数量低于预警阈值的商品列表
 *
     * @return 预警列表
 */
    @Override
    public List<StoreAlert> findAll() {
        return storeAlertMapper.findAll();
    }

    /**
 * 发送库存预警邮件
     * 当存在库存预警时，向管理员发送预警邮件
 *
 */
    @Override
    public void sendEmail() {
        List<StoreAlert> storeAlerts = storeAlertMapper.findAllWarm();
        if (storeAlerts != null && storeAlerts.size() > 0) {
            try {
                StringBuilder content = new StringBuilder();
                content.append("库存预警商品：");
                for (StoreAlert alert : storeAlerts) {
                    content.append(alert.getName()).append(" ");
                }
                emailUtils.send("admin@erp.com", "库存预警通知", content.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}