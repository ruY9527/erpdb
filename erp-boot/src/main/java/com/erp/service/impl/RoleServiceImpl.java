package com.erp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.erp.mapper.RoleMapper;
import com.erp.mapper.RoleMenuSetMapper;
import com.erp.pojo.Role;
import com.erp.pojo.RoleMenu;
import com.erp.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 角色服务实现类
 *
 * @author baoyang
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private RoleMenuSetMapper roleMenuSetMapper;

    @Override
    public List<Role> list() {
        LambdaQueryWrapper<Role> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Role::getState, "1");
        return roleMapper.selectList(wrapper);
    }

    @Override
    public Page<Role> page(Integer page, Integer rows) {
        Page<Role> pageObj = new Page<>(page, rows);
        LambdaQueryWrapper<Role> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Role::getState, "1");
        return roleMapper.selectPage(pageObj, wrapper);
    }

    @Override
    public void add(Role role) {
        role.setState("1");
        roleMapper.insert(role);
    }

    @Override
    public void update(Role role) {
        roleMapper.updateById(role);
    }

    @Override
    public void delete(Long id) {
        // 先删除角色菜单关联
        LambdaQueryWrapper<RoleMenu> menuWrapper = new LambdaQueryWrapper<>();
        menuWrapper.eq(RoleMenu::getRoleId, id);
        roleMenuSetMapper.delete(menuWrapper);

        // 逻辑删除角色
        LambdaUpdateWrapper<Role> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(Role::getRid, id).set(Role::getState, "0");
        roleMapper.update(null, wrapper);
    }

    @Override
    public Role getById(Long id) {
        return roleMapper.selectById(id);
    }

    @Override
    public List<Long> getRoleMenus(Long roleId) {
        LambdaQueryWrapper<RoleMenu> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(RoleMenu::getRoleId, roleId);
        List<RoleMenu> roleMenus = roleMenuSetMapper.selectList(wrapper);

        return roleMenus.stream()
                .map(RoleMenu::getMenuId)
                .collect(Collectors.toList());
    }

    @Override
    public void updateRoleMenus(Long roleId, List<Long> menuIds) {
        // 先删除旧的关联
        LambdaQueryWrapper<RoleMenu> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(RoleMenu::getRoleId, roleId);
        roleMenuSetMapper.delete(wrapper);

        // 添加新的关联
        if (menuIds != null && !menuIds.isEmpty()) {
            for (Long menuId : menuIds) {
                RoleMenu roleMenu = new RoleMenu();
                roleMenu.setRoleId(roleId);
                roleMenu.setMenuId(menuId);
                roleMenuSetMapper.insert(roleMenu);
            }
        }
    }
}