package com.erp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.erp.pojo.ReturnOrders;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 退货订单 Mapper 接口
 *
 * @author baoyang
 */
public interface ReturnOrdersMapper extends BaseMapper<ReturnOrders> {

    Map selectOldOidBySupplierIdAndGoodsId(@Param("supplierId") String supplierId, @Param("gsid") Long gsid);
    
    void add(ReturnOrders returnOrders);
    
    List<ReturnOrders> findByType(@Param("type") String type, @Param("state") String state);
    
    ReturnOrders getPojoByRoid(Long roid);
    
    void doCheckByUpdate(ReturnOrders returnOrders);
    
    void outBank(ReturnOrders returnOrders);
    
    List<ReturnOrders> myFindByType(@Param("eid") Long eid, @Param("type") String type);

    ReturnOrders findByOrdersOid(Long ordersOid);
}