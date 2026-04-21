package com.erp.service;

import java.util.List;

/**
 * 角色员工关联服务接口
 *
 * 提供角色员工关联管理的核心业务功能，包括：
 * - 角色员工关联查询
 * - 角色员工关联更新
 *
 * @author baoyang System
 * @version 1.0
 */
public interface RoleEmpSetService {

    /**
 * 根据角色ID查询员工ID列表
 *
     * @param rid 角色ID
     * @return 员工ID列表
 */
    List<Long> findAll(Long rid);

    /**
 * 更新角色员工关联
 *
     * @param rid 角色ID
     * @param empIds 员工ID列表，逗号分隔
 */
    void updateRoleEmp(Long rid, String empIds);
}