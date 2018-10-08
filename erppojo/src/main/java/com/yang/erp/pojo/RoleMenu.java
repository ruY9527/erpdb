package com.yang.erp.pojo;

//角色和权限对应的关系表
public class RoleMenu
{
    private Long roleId;  //角色id
    private Long menuId;  //菜单id

    public Long getRoleId()
    {
        return roleId;
    }

    public void setRoleId(Long roleId)
    {
        this.roleId = roleId;
    }

    public Long getMenuId()
    {
        return menuId;
    }

    public void setMenuId(Long menuId)
    {
        this.menuId = menuId;
    }

    @Override
    public String toString()
    {
        return "RoleMenu{" +
                "roleId=" + roleId +
                ", menuId=" + menuId +
                '}';
    }
}
