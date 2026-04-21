package com.erp.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 角色菜单关联
 *
 * @author baoyang
 */
@TableName("role_menu")
public class RoleMenu {

    @TableField("roleId")
    private Long roleId;

    @TableField("menuId")
    private Long menuId;

    public Long getRoleId() { return roleId; }
    public void setRoleId(Long roleId) { this.roleId = roleId; }
    public Long getMenuId() { return menuId; }
    public void setMenuId(Long menuId) { this.menuId = menuId; }
}