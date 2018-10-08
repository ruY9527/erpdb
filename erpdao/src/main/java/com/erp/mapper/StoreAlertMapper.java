package com.erp.mapper;

import com.yang.erp.pojo.StoreAlert;

import java.util.List;

public interface StoreAlertMapper
{
    public List<StoreAlert> findAll();
    public List<StoreAlert> findAllWarm();
}
