package com.erp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.erp.pojo.Menu;

import java.util.List;

/**
 * 菜单 Mapper 接口
 *
 * @author baoyang
 */
public interface MenuMapper extends BaseMapper<Menu> {

    List<Menu> findAll();
    
    List<Menu> findMenuByPid(Long pid);
    
    Menu getMenuById(Long id);
    
    Integer getTotal();
    
    List<Menu> selectMenuByMenu(Menu menu);
    
    Menu queryMenuTreeList(String pid);
    
    List<Menu> queryMenuTreeBtEmpId(Long eid);
}