package com.erp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.erp.pojo.Dept;

import java.util.List;

/**
 * 部门 Mapper 接口
 *
 * @author baoyang
 */
public interface DeptMapper extends BaseMapper<Dept> {

    List<Dept> findAll();
    
    List<Dept> selectDeptByNameOrTele(Dept dept);
    
    Integer getTotal();
    
    void add(Dept dept);
    
    void deleteDeptById(Long id);
    
    Dept getDeptById(Long id);
    
    void updtaeDept(Dept dept);
    
    List<Dept> selectDeptByDept(Dept dept);
}