package com.erp.service;

import java.util.List;

/**
 * 角色菜单关联服务接口
 *
 * 提供角色菜单权限关联管理的核心业务功能，包括：
 * - 角色菜单权限查询
 * - 角色菜单权限更新
 *
 * @author baoyang System
 * @version 1.0
 */
public interface RoleMenuSetService {

    /**
 * 根据角色ID查询菜单权限ID列表
 *
     * @param rid 角色ID
     * @return 菜单ID列表
 */
    List<String> findAll(Long rid);

    /**
 * 更新角色菜单权限
 *
     * @param rid 角色ID
     * @param menus 菜单ID列表，逗号分隔
 */
    void updateRoleMenu(Long rid, String menus);
}