package com.erp.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.erp.pojo.Store;

import java.util.List;

/**
 * 仓库服务接口
 *
 * @author baoyang
 */
public interface StoreService {

    List<Store> list();

    Page<Store> page(Integer page, Integer rows, String name);

    void add(Store store);

    void update(Store store);

    void delete(Long id);

    Store getById(Long id);
}