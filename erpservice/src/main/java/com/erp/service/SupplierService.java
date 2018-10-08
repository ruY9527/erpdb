package com.erp.service;

import com.erp.utils.MyResult;
import com.yang.erp.pojo.Supplier;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

public interface SupplierService {
    public List<Supplier> findByType(String type);

    public void add(Supplier supplier);

    public Supplier getPojoById(Long suid);

    public void update(Supplier supplier);

    public void delete(Long suid);

    public List<Supplier> findBySupplier(Supplier supplier,Integer page,Integer rows);

    public Integer selectCountByType(String type);
    //导出
    public void export(Supplier supplier,OutputStream outputStream);
    //导入
    public MyResult doImport(InputStream fileInputStream);
}
