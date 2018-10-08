package com.erp.service;

import com.yang.erp.pojo.Orders;

import java.util.List;

public interface OrdersService {
    public List<Orders> findByType(String type,String state);

    public void updateStateByOid(Long eid, Long oid);

    public List<Orders> myFindByType(Long eid, String type);

    public void doStart(Long eid, Long oid);
}
