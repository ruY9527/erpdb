package com.erp.service.impl;

import com.erp.mapper.WaybilldetailMapper;
import com.erp.service.WaybilldetailService;
import com.yang.erp.pojo.WayBillDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WaybilldetailServiceImpl implements WaybilldetailService
{
    @Autowired
    private WaybilldetailMapper waybilldetailMapper;

    @Override
    public WayBillDetail findBySn(Long sn)
    {
        return waybilldetailMapper.findBySn(sn);
    }
}
