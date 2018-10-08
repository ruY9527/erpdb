package com.erp.service;

import com.erp.utils.MyResult;
import com.yang.erp.pojo.Dept;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

public interface DeptService {
    public List<Dept> findAll();

    public List<Dept> selectDeptByNameOrTele(Dept dept,Integer page,Integer rows);

    public Integer getTotal();

    public void add(Dept dept);

    public void deleteDeptById(Long id);

    public Dept getDeptById(Long id);

    public void updateDept(Dept dept);

    public void export(Dept dept, OutputStream outputStream);

    public MyResult doImport(InputStream inputStream);
}
