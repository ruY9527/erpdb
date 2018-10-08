package com.erp.service;

import com.yang.erp.pojo.Tree;

import java.util.List;

public interface RoleEmpSetService
{
    public List<Tree> findAll(Long eid);

    public void updateRoleEmp(Long eid, String checkedStr);
}
