package com.erp.mapper;

import com.yang.erp.pojo.Role;

import java.util.List;

public interface RoleMapper
{
    public List<Role> findAll(Role role);

    public Integer selectCount();

    public void add(Role role);

    public Role getPojoById(Long rid);


    public void update(Role role);

    public void delete(Long rid);

    public List<Role> selectAll();

    public List<Role> selectRoleByEmpRole(Long eid);

    public Role getRoleMenuByRid(Long rid);
}
