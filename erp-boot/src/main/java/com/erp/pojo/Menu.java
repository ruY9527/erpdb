package com.erp.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.List;

/**
 * 菜单实体
 *
 * @author baoyang
 */
@TableName("menu")
public class Menu {

    @TableId("menuId")
    private Long menuid;

    @TableField("menuName")
    private String menuname;

    private String url;
    private String icon;
    private Long pid;
    private String state;

    @TableField(exist = false)
    private List<Menu> menus;

    public Long getMenuid() { return menuid; }
    public void setMenuid(Long menuid) { this.menuid = menuid; }
    public String getMenuname() { return menuname; }
    public void setMenuname(String menuname) { this.menuname = menuname; }
    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }
    public String getIcon() { return icon; }
    public void setIcon(String icon) { this.icon = icon; }
    public Long getPid() { return pid; }
    public void setPid(Long pid) { this.pid = pid; }
    public String getState() { return state; }
    public void setState(String state) { this.state = state; }
    public List<Menu> getMenus() { return menus; }
    public void setMenus(List<Menu> menus) { this.menus = menus; }
}