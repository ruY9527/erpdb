package com.erp.service;

import com.yang.erp.pojo.Menu;

import java.util.List;
import java.util.Map;

public interface MenuService {
    public Menu queryTreeList(String pid);

    public Menu queryMenuTreeBtEmpId(Long eid);
}
