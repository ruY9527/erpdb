package com.erp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.erp.pojo.Role;

import java.util.List;

/**
 * 角色 Mapper 接口
 *
 * @author baoyang
 */
public interface RoleMapper extends BaseMapper<Role> {

    List<Role> findAll(Role role);
    
    Role getPojoById(Long rid);
    
    Integer selectCount();
    
    void add(Role role);
    
    void update(Role role);
    
    void delete(Long rid);
    
    List<Role> selectAll();
    
    Role getRoleMenuByRid(Long rid);
    
    List<Role> selectRoleByEmpRole(Long eid);
}