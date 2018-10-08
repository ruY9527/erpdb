package com.erp.mapper;

import com.yang.erp.pojo.StoreOper;

import java.util.List;

public interface StoreoperMapper {
    public void add(StoreOper storeOper);
    public List<StoreOper> findAll(StoreOper storeOper);

    public Integer selectCountAll();
}
