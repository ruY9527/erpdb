package com.erp.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.erp.pojo.Storedetail;

import java.util.List;
import java.util.Map;

/**
 * 库存详情服务接口
 *
 * @author baoyang
 */
public interface StoredetailService {

    List<Storedetail> list();

    Page<Storedetail> page(Integer page, Integer rows, Long storeId, Long goodsId);

    Storedetail getById(Long id);

    Map<String, Object> stats();
}