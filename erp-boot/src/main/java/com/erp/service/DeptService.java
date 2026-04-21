package com.erp.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.erp.pojo.Dept;

import java.util.List;

/**
 * 部门服务接口
 *
 * @author baoyang
 */
public interface DeptService {

    List<Dept> list();

    Page<Dept> page(Integer page, Integer rows, String name);

    void add(Dept dept);

    void update(Dept dept);

    void delete(Long id);

    Dept getById(Long id);
}