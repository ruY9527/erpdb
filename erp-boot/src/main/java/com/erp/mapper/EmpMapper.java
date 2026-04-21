package com.erp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.erp.pojo.Emp;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 员工 Mapper 接口
 *
 * @author baoyang
 */
public interface EmpMapper extends BaseMapper<Emp> {

    List<Emp> findAll();
    
    void addEmp(Emp emp);
    
    void deleteEmp(Long id);
    
    List<Emp> selectByEmp(Emp emp);
    
    Integer getCount();
    
    Emp getEmpById(Long id);
    
    void updateEmp(Emp emp);
    
    Emp findEmpByUsername(@Param("username") String username);

    Emp findEmpByUsernameAndPwd(@Param("username") String username, @Param("pwd") String pwd);
    
    Emp checkEmpByEidAndPwd(@Param("eid") Long eid, @Param("pwd") String pwd);
    
    void updatePwdByEid(@Param("eid") Long eid, @Param("pwd") String pwd);
    
    List<Emp> selectAll();
    
    Emp selectEmpAndRole(Long eid);
}