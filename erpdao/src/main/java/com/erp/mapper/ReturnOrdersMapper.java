package com.erp.mapper;

import com.yang.erp.pojo.ReturnOrders;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ReturnOrdersMapper
{
    public Map selectOldOidBySupplierIdAndGoodsId(@Param("supplierId") String supplierId, @Param("gsid") Long gsid);

    public void add(ReturnOrders returnOrders);

    public List<ReturnOrders> findByType(@Param("type") String type,@Param("state") String state);

    public ReturnOrders getPojoByRoid(Long roid);

    public void doCheckByUpdate(ReturnOrders returnOrders);

    public void outBank(ReturnOrders returnOrders);

    public List<ReturnOrders> myFindByType(@Param("eid") Long eid,@Param("type") String type);
}
