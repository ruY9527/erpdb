package com.erp.service.impl;

import com.erp.mapper.EmpMapper;
import com.erp.mapper.RoleEmpMapper;
import com.erp.mapper.RoleMapper;
import com.erp.service.RoleEmpSetService;
import com.erp.pojo.Role;
import com.erp.pojo.RoleEmp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 角色员工关联服务实现类
 *
 * 实现角色员工关联管理的核心业务逻辑，包括：
 * - 角色员工关联查询
 * - 角色员工关联更新
 *
 * @author baoyang System
 * @version 1.0
 */
@Service
public class RoleEmpSetServiceImpl implements RoleEmpSetService {

    @Autowired
    private EmpMapper empMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private RoleEmpMapper roleEmpMapper;

    /**
 * 根据角色ID查询员工ID列表
 *
     * @param rid 角色ID
     * @return 员工ID列表
 */
    @Override
    public List<Long> findAll(Long rid) {
        List<Long> empIds = new ArrayList<>();
        List<RoleEmp> roleEmps = roleEmpMapper.findByRid(rid);
        if (roleEmps != null) {
            for (RoleEmp roleEmp : roleEmps) {
                empIds.add(roleEmp.getEmpId());
            }
        }
        return empIds;
    }

    /**
 * 更新角色员工关联
 *
     * @param rid 角色ID
     * @param empIds 员工ID列表，逗号分隔
 */
    @Override
    public void updateRoleEmp(Long rid, String empIds) {
        // 删除旧的关联
        Integer count = roleEmpMapper.selectCountByRid(rid);
        if (count != null && count > 0) {
            roleEmpMapper.deleteAllByRid(rid);
        }
        // 插入新的关联
        if (empIds != null && !empIds.isEmpty()) {
            String[] ids = empIds.split(",");
            for (String id : ids) {
                RoleEmp roleEmp = new RoleEmp();
                roleEmp.setRid(rid);
                roleEmp.setEmpId(Long.valueOf(id));
                roleEmpMapper.add(roleEmp);
            }
        }
    }
}