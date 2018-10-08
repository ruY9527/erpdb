package com.erp.service.impl;

import com.erp.mapper.RoleMapper;
import com.erp.service.RoleService;
import com.github.pagehelper.PageHelper;
import com.yang.erp.pojo.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService
{

    @Autowired
    private RoleMapper roleMapper;

    //查询根据条件或者全部
    @Override
    public List<Role> findAll(Role role, Integer page, Integer rows)
    {
        PageHelper.startPage(page,rows);
        List<Role> roleList =  roleMapper.findAll(role);
        return roleList;
    }

    //查询个数
    @Override
    public Integer selectCount()
    {
        return roleMapper.selectCount();
    }

    //添加
    @Override
    public void add(Role role)
    {
        roleMapper.add(role);
    }

    //根据rid获取
    @Override
    public Role getPojoById(Long rid)
    {
        return roleMapper.getPojoById(rid);
    }

    //更新
    @Override
    public void update(Role role)
    {
        roleMapper.update(role);
    }

    //虚拟删除
    @Override
    public void delete(Long rid)
    {
        roleMapper.delete(rid);
    }

    //无条件查询全部
    @Override
    public List<Role> selectAll()
    {
        return roleMapper.selectAll();
    }
}
