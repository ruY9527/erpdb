package com.erp.mapper;

import com.yang.erp.pojo.Emp;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/*
员工表
 */
public interface EmpMapper {

    //获取全部的信息
    public List<Emp> findAll();

    //添加信息
    public void addEmp(Emp emp);

    //根据id删除信息
    public void deleteEmp(Long id);

    //根据条件查询
    public List<Emp> selectByEmp(Emp emp);

    //获取总个数
    public Integer getCount();

    //根据id获取一个对象信息
    public Emp getEmpById(Long id);

    //根据id来更新信息
    public void updateEmp(Emp emp);

    //登录操作
    public Emp findEmpByUsernameAndPwd(@Param("username") String username, @Param("pwd") String pwd);

    //根据前端传递过来的id和密码进行是否输入正确的判断
    public Emp checkEmpByEidAndPwd(@Param("eid") Long eid,@Param("pwd") String pwd);

    //根据id来更新密码
    public void updatePwdByEid(@Param("eid") Long eid,@Param("pwd") String pwd);

    public List<Emp> selectAll();

    public Emp selectEmpAndRole(Long eid);
}
