package com.erp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.erp.mapper.SupplierMapper;
import com.erp.pojo.Supplier;
import com.erp.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 供应商服务实现类
 *
 * @author baoyang
 */
@Service
public class SupplierServiceImpl implements SupplierService {

    @Autowired
    private SupplierMapper supplierMapper;

    @Override
    public List<Supplier> listByType(String type) {
        LambdaQueryWrapper<Supplier> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Supplier::getType, type);
        wrapper.eq(Supplier::getState, "1");
        return supplierMapper.selectList(wrapper);
    }

    @Override
    public List<Supplier> list() {
        LambdaQueryWrapper<Supplier> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Supplier::getState, "1");
        return supplierMapper.selectList(wrapper);
    }

    @Override
    public Page<Supplier> page(Integer page, Integer rows, String type, String name) {
        Page<Supplier> pageObj = new Page<>(page, rows);
        LambdaQueryWrapper<Supplier> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Supplier::getState, "1");

        if (type != null && !type.isEmpty()) {
            wrapper.eq(Supplier::getType, type);
        }
        if (name != null && !name.isEmpty()) {
            wrapper.like(Supplier::getName, name);
        }

        return supplierMapper.selectPage(pageObj, wrapper);
    }

    @Override
    public void add(Supplier supplier) {
        supplier.setState("1");
        supplierMapper.insert(supplier);
    }

    @Override
    public void update(Supplier supplier) {
        supplierMapper.updateById(supplier);
    }

    @Override
    public void delete(Long id) {
        LambdaUpdateWrapper<Supplier> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(Supplier::getSuid, id).set(Supplier::getState, "0");
        supplierMapper.update(null, wrapper);
    }

    @Override
    public Supplier getById(Long id) {
        return supplierMapper.selectById(id);
    }
}