package com.erp.mapper;

import com.yang.erp.pojo.WayBill;

import java.util.List;

public interface WaybillMapper
{
    public List<WayBill> findAll(WayBill wayBill);

    public void add(WayBill wayBill);

    public WayBill getPojoById(Long sn);

    public void update(WayBill wayBill);

    public void delete(Long sn);

    public Integer selectCount();
}
