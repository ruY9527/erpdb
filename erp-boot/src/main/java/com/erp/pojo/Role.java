package com.erp.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.List;

/**
 * 角色
 *
 * @author baoyang
 */
@TableName("role")
public class Role {

    @TableId("rid")
    private Long rid;

    private String name;

    private String state;

    @TableField(exist = false)
    private String menuIds;

    @TableField(exist = false)
    private List<Menu> menus;

    public Long getRid() { return rid; }
    public void setRid(Long rid) { this.rid = rid; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getState() { return state; }
    public void setState(String state) { this.state = state; }
    public String getMenuIds() { return menuIds; }
    public void setMenuIds(String menuIds) { this.menuIds = menuIds; }
    public List<Menu> getMenus() { return menus; }
    public void setMenus(List<Menu> menus) { this.menus = menus; }
}