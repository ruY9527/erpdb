package com.erp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.erp.pojo.RoleMenu;
import org.apache.ibatis.annotations.Param;

/**
 * 角色-菜单关联 Mapper 接口
 *
 * @author baoyang
 */
public interface RoleMenuSetMapper extends BaseMapper<RoleMenu> {

    void add(RoleMenu roleMenu);

    Integer selectCountByRid(@Param("rid") Long rid);

    void deleteAllByRid(@Param("rid") Long rid);
}