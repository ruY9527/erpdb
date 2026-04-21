package com.erp.service;

import com.erp.pojo.GoodsType;

import java.util.List;

/**
 * 商品类型服务接口
 *
 * @author baoyang
 */
public interface GoodsTypeService {

    List<GoodsType> list();

    void add(GoodsType goodsType);

    void update(GoodsType goodsType);

    void delete(Long id);

    GoodsType getById(Long id);
}