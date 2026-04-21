package com.erp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.erp.pojo.Emp;
import com.erp.pojo.Orders;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 订单 Mapper 接口
 *
 * @author baoyang
 */
public interface OrdersMapper extends BaseMapper<Orders> {

    void add(Orders orders);
    
    List<Orders> findAllByTypeAndState(@Param("type") String type, @Param("state") String state);
    
    void updateStateByOid(@Param("eid") Long eid, @Param("oid") Long oid);
    
    Emp findEmpByCreateer(Long createer);
    
    Emp findEmpByChecker(Long checker);
    
    Emp findEmpByStarter(Long starter);
    
    Emp findEmpByEnder(Long ender);
    
    List<Orders> myFindByType(Map map);
    
    Orders findForUpdateByOid(Long ordersId);
    
    void updateOutByOrders(Orders orders);
    
    void doStart(@Param("eid") Long eid, @Param("oid") Long oid);
    
    void updateAddStoreState(@Param("eid") Long eid, @Param("ordersId") Long ordersId);
}