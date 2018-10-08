package com.erp.service;

import com.yang.erp.pojo.Tree;

import java.util.List;

public interface RoleMenuSetService
{
    public List<Tree> findAll(Long rid);

    public void updateRoleMenus(Long rid, String checkedStr);
}
