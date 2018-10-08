package com.yang.erp.pojo;

import java.util.List;

public class Menu {
    private String menuid;//菜单ID
    private String menuname;//菜单名称
    private String icon;//图标
    private String url;//URL
    private List<Menu> menus;
    private String pid;
    private boolean isParent;  //是否是顶级目录

    public String getMenuid()
    {
        return menuid;
    }

    public void setMenuid(String menuid)
    {
        this.menuid = menuid;
    }

    public String getMenuname()
    {
        return menuname;
    }

    public void setMenuname(String menuname)
    {
        this.menuname = menuname;
    }

    public String getIcon()
    {
        return icon;
    }

    public void setIcon(String icon)
    {
        this.icon = icon;
    }

    public String getUrl()
    {
        return url;
    }

    public void setUrl(String url)
    {
        this.url = url;
    }

    public List<Menu> getMenus()
    {
        return menus;
    }

    public void setMenus(List<Menu> menus)
    {
        this.menus = menus;
    }

    public String getPid()
    {
        return pid;
    }

    public void setPid(String pid)
    {
        this.pid = pid;
    }

    public boolean isParent()
    {
        return isParent;
    }

    public void setParent(boolean parent)
    {
        isParent = parent;
    }

    @Override
    public String toString()
    {
        return "Menu{" +
                "menuid='" + menuid + '\'' +
                ", menuname='" + menuname + '\'' +
                ", icon='" + icon + '\'' +
                ", url='" + url + '\'' +
                ", menus=" + menus +
                ", pid='" + pid + '\'' +
                ", isParent=" + isParent +
                '}';
    }
}