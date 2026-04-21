package com.erp.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.erp.pojo.Goods;

import java.util.List;

/**
 * 商品服务接口
 *
 * @author baoyang
 */
public interface GoodsService {

    List<Goods> list();

    Page<Goods> page(Integer page, Integer rows, String name);

    void add(Goods goods);

    void update(Goods goods);

    void delete(Long id);

    Goods getById(Long id);
}