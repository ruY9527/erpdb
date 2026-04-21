package com.erp.service.impl;

import com.erp.mapper.OrdersMapper;
import com.erp.service.OrdersService;
import com.erp.pojo.Emp;
import com.erp.pojo.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 订单服务实现类
 *
 * 实现订单管理的核心业务逻辑，包括：
 * - 订单查询（采购/销售）
 * - 订单状态流转（审核、确认、入库）
 * - 订单相关人员信息填充
 *
 * @author baoyang System
 * @version 1.0
 */
@Service
public class OrdersServiceImpl implements OrdersService {

    @Autowired
    private OrdersMapper ordersMapper;

    /**
 * 根据类型和状态查询订单
     * 同时填充订单相关的四个人员信息（创建人、审核人、确认人、入库人）
 *
     * @param type 订单类型：1-采购订单，2-销售订单
     * @param state 订单状态
     * @return 订单列表（包含人员信息）
 */
    @Override
    public List<Orders> findByType(String type, String state) {
        List<Orders> ordersList = ordersMapper.findAllByTypeAndState(type, state);
        fillFourEmp(ordersList);
        return ordersList;
    }

    /**
 * 更新订单状态
     * 将订单状态更新为已审核，记录审核人
 *
     * @param eid 审核员工ID
     * @param oid 订单ID
 */
    @Override
    public void updateStateByOid(Long eid, Long oid) {
        ordersMapper.updateStateByOid(eid, oid);
    }

    /**
 * 查询员工相关的订单
     * 只查询当前登录员工自己创建的订单
 *
     * @param eid 员工ID
     * @param type 订单类型
     * @return 订单列表
 */
    @Override
    public List<Orders> myFindByType(Long eid, String type) {
        Map map = new HashMap();
        map.put("eid", eid);
        map.put("type", type);
        List<Orders> orders = ordersMapper.myFindByType(map);
        fillFourEmp(orders);
        return orders;
    }

    /**
 * 订单确认入库/出库
     * 记录确认入库/出库操作的人员和时间
 *
     * @param eid 操作员工ID
     * @param oid 订单ID
 */
    @Override
    public void doStart(Long eid, Long oid) {
        ordersMapper.doStart(eid, oid);
    }

    /**
 * 填充订单相关的四个人员信息
     * 包括：创建人(emp)、审核人(emp2)、确认人(emp3)、入库人(emp4)
 *
     * @param ordersList 订单列表
 */
    private void fillFourEmp(List<Orders> ordersList) {
        for (Orders order : ordersList) {
            // 创建人信息
            if (order.getCreateer() != null) {
                Emp emp = ordersMapper.findEmpByCreateer(order.getCreateer());
                order.setEmp(emp);
            }
            // 审核人信息
            if (order.getChecker() != null) {
                Emp emp2 = ordersMapper.findEmpByChecker(order.getChecker());
                order.setEmp2(emp2);
            }
            // 确认人信息
            if (order.getStarter() != null) {
                Emp emp3 = ordersMapper.findEmpByStarter(order.getStarter());
                order.setEmp3(emp3);
            }
            // 入库人信息
            if (order.getEnder() != null) {
                Emp emp4 = ordersMapper.findEmpByEnder(order.getEnder());
                order.setEmp4(emp4);
            }
        }
    }
}