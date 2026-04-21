package com.erp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.erp.mapper.DeptMapper;
import com.erp.pojo.Dept;
import com.erp.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 部门服务实现类
 *
 * @author baoyang
 */
@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptMapper deptMapper;

    @Override
    public List<Dept> list() {
        LambdaQueryWrapper<Dept> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Dept::getState, "1");
        return deptMapper.selectList(wrapper);
    }

    @Override
    public Page<Dept> page(Integer page, Integer rows, String name) {
        Page<Dept> pageObj = new Page<>(page, rows);
        LambdaQueryWrapper<Dept> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Dept::getState, "1");

        if (name != null && !name.isEmpty()) {
            wrapper.like(Dept::getName, name);
        }

        return deptMapper.selectPage(pageObj, wrapper);
    }

    @Override
    public void add(Dept dept) {
        dept.setState("1");
        deptMapper.insert(dept);
    }

    @Override
    public void update(Dept dept) {
        deptMapper.updateById(dept);
    }

    @Override
    public void delete(Long id) {
        LambdaUpdateWrapper<Dept> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(Dept::getDid, id).set(Dept::getState, "0");
        deptMapper.update(null, wrapper);
    }

    @Override
    public Dept getById(Long id) {
        return deptMapper.selectById(id);
    }
}