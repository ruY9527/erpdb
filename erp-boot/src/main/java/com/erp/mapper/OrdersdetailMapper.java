package com.erp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.erp.pojo.Ordersdetail;

import java.util.List;

/**
 * 订单明细 Mapper 接口
 *
 * @author baoyang
 */
public interface OrdersdetailMapper extends BaseMapper<Ordersdetail> {

    void add(Ordersdetail ordersdetail);
    
    Ordersdetail findByOid(Long odid);
    
    void updateByOrderDetail(Ordersdetail ordersdetail);
    
    void updateOutByOrdersdetail(Ordersdetail ordersdetail);
    
    Long selectCountByOrdersIdAndState(Long ordersId);

    void update(Ordersdetail ordersdetail);

    void delete(Long odid);
}