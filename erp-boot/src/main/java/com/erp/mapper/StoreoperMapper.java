package com.erp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.erp.pojo.StoreOper;

import java.util.List;

/**
 * 仓库操作记录 Mapper 接口
 *
 * @author baoyang
 */
public interface StoreoperMapper extends BaseMapper<StoreOper> {

    void add(StoreOper storeOper);
    
    List<StoreOper> findAll(StoreOper storeOper);
    
    Integer selectCountAll();
}