package com.erp.service;

import com.yang.erp.pojo.Emp;
import com.yang.erp.pojo.Ordersdetail;

import java.util.List;

public interface OrdersdetailService {
    public void add(Emp emp,String supplierId, List<Ordersdetail> ordersdetails,String type);
}
