package com.erp.service;

import com.erp.utils.MyResult;
import com.yang.erp.pojo.StoreOper;

import java.util.List;

public interface StoreOperService {
    public void addAndUpdate(Long eid, Long odid, Long storeId);

    public MyResult out(Long odid, Long storeId, Long eid);

    public List<StoreOper> findAll(StoreOper storeOper,Integer page,Integer rows);

    public Integer selectCountAll();
}
