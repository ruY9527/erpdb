package com.erp.service;

import com.yang.erp.pojo.Role;

import java.util.List;

public interface RoleService
{
    public List<Role> findAll(Role role, Integer page, Integer rows);

    public Integer selectCount();

    public void add(Role role);

    public Role getPojoById(Long rid);

    public void update(Role role);

    public void delete(Long rid);

    public List<Role> selectAll();
}
