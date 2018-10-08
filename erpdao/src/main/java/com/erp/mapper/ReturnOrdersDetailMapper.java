package com.erp.mapper;

import com.yang.erp.pojo.ReturnOrdersDetail;

public interface ReturnOrdersDetailMapper
{
    public void add(ReturnOrdersDetail returnOrdersDetail);

    public ReturnOrdersDetail selectPojoByRosd(Long rosd);


    public void updateOutBank(ReturnOrdersDetail returnOrdersDetail);

    public Integer selectCountByReturnOrdersId(Long returnordersId);
}
