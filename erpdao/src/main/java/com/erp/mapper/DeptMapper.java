package com.erp.mapper;

import com.yang.erp.pojo.Dept;

import java.util.List;


public interface DeptMapper {
    //获取所有的个数
    public List<Dept> findAll();

    //根据条件来获取
    public List<Dept> selectDeptByNameOrTele(Dept dept);

    //获取所有的信息个数
    public Integer getTotal();

    //添加信息
    public void add(Dept dept);

    //删除方法
    public void deleteDeptById(Long id);

    //根据id来获取一个对象，用于修改操作
    public Dept getDeptById(Long id);

    //修改的信息操作
    public void updtaeDept(Dept dept);

    //根据dept来查询
    public List<Dept> selectDeptByDept(Dept dept);
}
