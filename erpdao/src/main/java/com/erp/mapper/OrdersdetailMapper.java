package com.erp.mapper;

import com.yang.erp.pojo.Ordersdetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrdersdetailMapper {

    public void add(Ordersdetail ordersdetail);
    public Ordersdetail findByOid(Long odid);
    public void updateByOrderDetail(Ordersdetail ordersdetail);

    public void updateOutByOrdersdetail(Ordersdetail ordersdetail);

    public Long selectCountByOrdersIdAndState(Long ordersId);
}
