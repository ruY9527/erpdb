package com.erp.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.erp.pojo.Role;

import java.util.List;

/**
 * 角色服务接口
 *
 * @author baoyang
 */
public interface RoleService {

    List<Role> list();

    Page<Role> page(Integer page, Integer rows);

    void add(Role role);

    void update(Role role);

    void delete(Long id);

    Role getById(Long id);

    List<Long> getRoleMenus(Long roleId);

    void updateRoleMenus(Long roleId, List<Long> menuIds);
}