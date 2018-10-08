package com.erp.service;

import com.erp.utils.MyResult;
import com.yang.erp.pojo.Dept;
import com.yang.erp.pojo.Emp;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

public interface EmpService {
    public List<Emp> findAll();
    public void addEmp(Emp emp);
    public void deleteEmp(Long id);
    public List<Emp> selectByEmp(Emp emp,Integer page,Integer rows);
    public Integer getCount();
    public Emp getEmpById(Long id);
    public void updateEmp(Emp emp);
    public Emp findEmpByUsernameAndPwd(String username,String pwd);
    public Emp checkEmpByEidAndPwd(Long eid,String pwd);
    public void updatePwdByEid(Long eid,String pwd);
    public List<Emp> selectAll();

    public void export(Emp emp, OutputStream outputStream);

    public MyResult doImport(InputStream inputStream);
}
