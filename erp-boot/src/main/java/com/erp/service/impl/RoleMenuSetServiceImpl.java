package com.erp.service.impl;

import com.erp.mapper.RoleMenuSetMapper;
import com.erp.mapper.MenuMapper;
import com.erp.mapper.RoleMapper;
import com.erp.service.RoleMenuSetService;
import com.erp.pojo.Menu;
import com.erp.pojo.Role;
import com.erp.pojo.RoleMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 角色菜单关联服务实现类
 *
 * 实现角色菜单权限关联管理的核心业务逻辑，包括：
 * - 角色菜单权限查询
 * - 角色菜单权限更新
 *
 * @author baoyang System
 * @version 1.0
 */
@Service
public class RoleMenuSetServiceImpl implements RoleMenuSetService {

    @Autowired
    private RoleMenuSetMapper roleMenuSetMapper;

    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    private RoleMapper roleMapper;

    /**
 * 根据角色ID查询菜单权限ID列表
 *
     * @param rid 角色ID
     * @return 菜单ID列表
 */
    @Override
    public List<String> findAll(Long rid) {
        List<String> menuIds = new ArrayList<>();
        Role role = roleMapper.getRoleMenuByRid(rid);
        if (role != null && role.getMenus() != null) {
            for (Menu menu : role.getMenus()) {
                menuIds.add(String.valueOf(menu.getMenuid()));
            }
        }
        return menuIds;
    }

    /**
 * 更新角色菜单权限
 *
     * @param rid 角色ID
     * @param menus 菜单ID列表，逗号分隔
 */
    @Override
    public void updateRoleMenu(Long rid, String menus) {
        // 删除旧的关联
        Integer count = roleMenuSetMapper.selectCountByRid(rid);
        if (count != null && count > 0) {
            roleMenuSetMapper.deleteAllByRid(rid);
        }
        // 插入新的关联
        if (menus != null && !menus.isEmpty()) {
            String[] menuIds = menus.split(",");
            for (String menuId : menuIds) {
                RoleMenu roleMenu = new RoleMenu();
                roleMenu.setRoleId(rid);
                roleMenu.setMenuId(Long.valueOf(menuId));
                roleMenuSetMapper.add(roleMenu);
            }
        }
    }
}