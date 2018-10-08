package com.erp.mapper;

import com.yang.erp.pojo.Emp;
import com.yang.erp.pojo.Orders;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface OrdersMapper {

    public void add(Orders orders);

    public List<Orders> findAllByTypeAndState(@Param("type") String type,@Param("state") String state);

    public void updateStateByOid(@Param("eid") Long eid,@Param("oid") Long oid);

    //根据创建人信息来获取
    public Emp findEmpByCreateer(Long createer);

    //根据检查的id信息来查询
    public Emp findEmpByChecker(Long checker);

    //根据确认人
    public Emp findEmpByStarter(Long starter);

    //根据结束人来获取信息
    public Emp findEmpByEnder(Long ender);

    public List<Orders> myFindByType(Map map);

    public Orders findForUpdateByOid(Long ordersId);

    public void updateOutByOrders(Orders orders);

    public void doStart(@Param("eid") Long eid,@Param("oid") Long oid);

    public void updateAddStoreState(@Param("eid") Long eid,@Param("ordersId") Long ordersId);
}
