package com.erp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.erp.mapper.MenuMapper;
import com.erp.pojo.Menu;
import com.erp.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 菜单服务实现类
 *
 * @author baoyang
 */
@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<Menu> list() {
        LambdaQueryWrapper<Menu> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Menu::getState, "1");
        return menuMapper.selectList(wrapper);
    }

    @Override
    public Page<Menu> page(Integer page, Integer rows) {
        Page<Menu> pageObj = new Page<>(page, rows);
        LambdaQueryWrapper<Menu> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Menu::getState, "1");
        return menuMapper.selectPage(pageObj, wrapper);
    }

    @Override
    public List<Menu> tree() {
        LambdaQueryWrapper<Menu> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Menu::getState, "1");
        wrapper.orderByAsc(Menu::getMenuid);
        List<Menu> allMenus = menuMapper.selectList(wrapper);

        List<Menu> rootMenus = allMenus.stream()
                .filter(m -> m.getPid() == 0)
                .collect(Collectors.toList());

        rootMenus.forEach(root -> buildChildren(root, allMenus));

        return rootMenus;
    }

    private void buildChildren(Menu parent, List<Menu> allMenus) {
        List<Menu> children = allMenus.stream()
                .filter(m -> m.getPid() != null && m.getPid().equals(parent.getMenuid()))
                .collect(Collectors.toList());

        parent.setMenus(children);

        children.forEach(child -> buildChildren(child, allMenus));
    }

    @Override
    public Menu getById(Long id) {
        return menuMapper.selectById(id);
    }

    @Override
    public void add(Menu menu) {
        menu.setState("1");
        menuMapper.insert(menu);
    }

    @Override
    public void update(Menu menu) {
        menuMapper.updateById(menu);
    }

    @Override
    public void delete(Long id) {
        // 先删除子菜单
        LambdaUpdateWrapper<Menu> childWrapper = new LambdaUpdateWrapper<>();
        childWrapper.eq(Menu::getPid, id).set(Menu::getState, "0");
        menuMapper.update(null, childWrapper);

        // 再删除当前菜单
        LambdaUpdateWrapper<Menu> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(Menu::getMenuid, id).set(Menu::getState, "0");
        menuMapper.update(null, wrapper);
    }

    @Override
    public List<Menu> getByUserId(Long userId) {
        return menuMapper.queryMenuTreeBtEmpId(userId);
    }
}