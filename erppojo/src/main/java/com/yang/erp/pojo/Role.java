package com.yang.erp.pojo;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.ArrayList;
import java.util.List;

public class Role
{
    private Long rid;  //role的id
    private String name;  //角色名字

    @JSONField(serialize = false)
    private List<Menu> menus;  //角色下的菜单

    public Long getRid()
    {
        return rid;
    }

    public void setRid(Long rid)
    {
        this.rid = rid;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public List<Menu> getMenus()
    {
        if(menus == null){
            menus = new ArrayList<Menu>();
        }
        return menus;
    }

    public void setMenus(List<Menu> menus)
    {
        this.menus = menus;
    }

    @Override
    public String toString()
    {
        return "Role{" +
                "rid=" + rid +
                ", name='" + name + '\'' +
                ", menus=" + menus +
                '}';
    }
}
