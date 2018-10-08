package com.erp.service.impl;

import com.erp.mapper.StoredetailMapper;
import com.erp.service.StoredetailService;
import com.yang.erp.pojo.StoreDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoredetailServiceImpl implements StoredetailService
{
    @Autowired
    private StoredetailMapper storedetailMapper;

    //获取全部信息
    @Override
    public List<StoreDetail> findAll(StoreDetail storeDetail)
    {
        return storedetailMapper.findAll(storeDetail);
    }
}
