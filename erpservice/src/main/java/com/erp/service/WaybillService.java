package com.erp.service;

import com.yang.erp.pojo.WayBill;

import java.util.List;

public interface WaybillService
{
    public List<WayBill> findAll(WayBill wayBill, Integer page, Integer rows);

    public void add(WayBill wayBill);

    public WayBill getPojoById(Long sn);

    public void update(WayBill wayBill);

    public void delete(Long sn);

    public Integer selectCount();
}
