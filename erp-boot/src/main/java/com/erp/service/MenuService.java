package com.erp.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.erp.pojo.Menu;

import java.util.List;

/**
 * 菜单服务接口
 *
 * @author baoyang
 */
public interface MenuService {

    List<Menu> list();

    Page<Menu> page(Integer page, Integer rows);

    List<Menu> tree();

    Menu getById(Long id);

    void add(Menu menu);

    void update(Menu menu);

    void delete(Long id);

    List<Menu> getByUserId(Long userId);
}