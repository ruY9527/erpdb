package com.erp.service;

import com.erp.utils.MyResult;
import com.yang.erp.pojo.Goods;
import com.yang.erp.pojo.ReturnOrders;
import com.yang.erp.pojo.ReturnOrdersDetail;

import java.util.List;

public interface ReturnOrdersService
{
    public MyResult add(Long eid, String type, String supplierId, List<ReturnOrdersDetail> returnOrdersDetailList);

    //查询，全部或者条件查询
    public List<ReturnOrders> findByType(String type, String state);

    public MyResult doCheck(Long eid, Long roid);

    public MyResult outBank(Long rosd, Long storeId,Long eid);

    public List<ReturnOrders> myFindByType(Long eid,String type);
}
