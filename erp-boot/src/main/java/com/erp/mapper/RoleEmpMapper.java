package com.erp.mapper;

import com.erp.pojo.RoleEmp;

import java.util.List;

/**
 * 角色-员工关联 Mapper 接口
 *
 * @author baoyang
 */
public interface RoleEmpMapper {

    void add(RoleEmp roleEmp);

    Integer selectHaveByEid(Long eid);

    void deleteAllByEid(Long eid);

    List<RoleEmp> findByRid(Long rid);

    Integer selectCountByRid(Long rid);

    void deleteAllByRid(Long rid);
}