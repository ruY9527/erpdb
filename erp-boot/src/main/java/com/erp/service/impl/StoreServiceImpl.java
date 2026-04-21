package com.erp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.erp.mapper.EmpMapper;
import com.erp.mapper.StoreMapper;
import com.erp.pojo.Emp;
import com.erp.pojo.Store;
import com.erp.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * 仓库服务实现类
 *
 * @author baoyang
 */
@Service
public class StoreServiceImpl implements StoreService {

    @Autowired
    private StoreMapper storeMapper;

    @Autowired
    private EmpMapper empMapper;

    @Override
    public List<Store> list() {
        LambdaQueryWrapper<Store> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Store::getState, Store.USE);
        List<Store> list = storeMapper.selectList(wrapper);
        fillEmpData(list);
        return list;
    }

    @Override
    public Page<Store> page(Integer page, Integer rows, String name) {
        Page<Store> pageObj = new Page<>(page, rows);
        LambdaQueryWrapper<Store> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Store::getState, Store.USE);

        if (name != null && !name.isEmpty()) {
            wrapper.like(Store::getName, name);
        }

        Page<Store> result = storeMapper.selectPage(pageObj, wrapper);
        fillEmpData(result.getRecords());
        return result;
    }

    @Override
    public void add(Store store) {
        store.setState(Store.USE);
        storeMapper.insert(store);
    }

    @Override
    public void update(Store store) {
        storeMapper.updateById(store);
    }

    @Override
    public void delete(Long id) {
        LambdaUpdateWrapper<Store> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(Store::getSid, id).set(Store::getState, Store.NO_USE);
        storeMapper.update(null, wrapper);
    }

    @Override
    public Store getById(Long id) {
        Store store = storeMapper.selectById(id);
        if (store != null) {
            fillEmpData(Collections.singletonList(store));
        }
        return store;
    }

    /**
 * 填充管理员信息
 */
    private void fillEmpData(List<Store> storeList) {
        for (Store store : storeList) {
            if (store.getEmpId() != null) {
                Emp emp = empMapper.selectById(store.getEmpId());
                store.setEmp(emp);
            }
        }
    }
}