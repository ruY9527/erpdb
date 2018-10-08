package com.erp.service;

import com.yang.erp.pojo.Store;

import java.io.OutputStream;
import java.util.List;

public interface StoreService {
    public List<Store> findAll(Store store,Integer page,Integer rows);
    public List<Store> findByEmpId(Long empId);

    public void add(Store store);
    public void delete(Long sid);
    public Store getPojoById(Long sid);
    public void update(Store store);
    public List<Store> selectAll();

    public void export(Store store, OutputStream outputStream);
}
