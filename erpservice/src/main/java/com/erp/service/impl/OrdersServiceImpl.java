package com.erp.service.impl;

import com.erp.mapper.OrdersMapper;
import com.erp.service.OrdersService;
import com.yang.erp.pojo.Emp;
import com.yang.erp.pojo.Orders;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrdersServiceImpl implements OrdersService
{

    @Autowired
    private OrdersMapper ordersMapper;

    //根据类型获取
    @Override
    public List<Orders> findByType(String type, String state)
    {
        List<Orders> ordersList = ordersMapper.findAllByTypeAndState(type, state);
        getFourEmp(ordersList);
        //((OrdersServiceImpl)AopContext.currentProxy()).updateStateByOid();
        return ordersList;
    }

    //根据eid和oid来修改数据库中信息(eid对应审核人)
    @Override
    public void updateStateByOid(Long eid, Long oid)
    {

        ordersMapper.updateStateByOid(eid, oid);
    }

    //根据当前登录信息来只查询当前登录自己的信息
    @Override
    public List<Orders> myFindByType(Long eid, String type)
    {
        Map map = new HashMap();
        map.put("eid",eid);
        map.put("type",type);
        List<Orders> orders =  ordersMapper.myFindByType(map);
        getFourEmp(orders);
        return orders;
    }

    //确认信息
    @Override
    public void doStart(Long eid, Long oid)
    {
        ordersMapper.doStart(eid,oid);
    }

    //遍历获取四个emp对象的操作
    public void getFourEmp(List<Orders> ordersList){
        for (Orders order : ordersList)
        {
            if (order.getCreateer() != null)
            {
                //创建人信息
                Emp emp = ordersMapper.findEmpByCreateer(order.getCreateer());
                order.setEmp(emp);
            }
            if (order.getCreateer() != null)
            {
                //检查人信息
                Emp emp2 = ordersMapper.findEmpByChecker(order.getChecker());
                order.setEmp2(emp2);
            }
            if (order.getStarter() != null)
            {
                //确认人信息
                Emp emp3 = ordersMapper.findEmpByStarter(order.getStarter());
                order.setEmp3(emp3);
            }
            if (order.getEnder() != null)
            {
                Emp emp4 = ordersMapper.findEmpByEnder(order.getEnder());
                order.setEmp4(emp4);
            }

        }
    }

}
