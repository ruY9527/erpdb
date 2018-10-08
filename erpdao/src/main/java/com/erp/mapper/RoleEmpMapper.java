package com.erp.mapper;

import com.yang.erp.pojo.RoleEmp;

public interface RoleEmpMapper
{

    public void add(RoleEmp roleEmp);

    public Integer selectHaveByEid(Long eid);

    public void deleteAllByEid(Long eid);
}
