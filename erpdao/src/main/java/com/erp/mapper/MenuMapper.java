package com.erp.mapper;


import java.util.List;
import java.util.Map;

import com.yang.erp.pojo.Menu;
import com.yang.erp.pojo.MenuExample;
import org.apache.ibatis.annotations.Param;

public interface MenuMapper {

    public Menu queryMenuTreeList(@Param("pid") String pid);

    public List<Menu> queryMenuTreeBtEmpId(Long eid);
}