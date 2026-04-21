package com.erp.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.erp.pojo.Supplier;

import java.util.List;

/**
 * 供应商服务接口
 *
 * @author baoyang
 */
public interface SupplierService {

    List<Supplier> listByType(String type);

    List<Supplier> list();

    Page<Supplier> page(Integer page, Integer rows, String type, String name);

    void add(Supplier supplier);

    void update(Supplier supplier);

    void delete(Long id);

    Supplier getById(Long id);
}