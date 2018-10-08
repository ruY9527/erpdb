package com.erp.mapper;

import com.yang.erp.pojo.RoleMenu;

public interface RoleMenuSetMapper
{
    public void add(RoleMenu roleMenu);

    public Integer selectCountByRid(Long rid);

    public void deleteAllByRid(Long rid);
}
