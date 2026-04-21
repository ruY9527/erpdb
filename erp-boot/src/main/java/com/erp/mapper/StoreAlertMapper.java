package com.erp.mapper;

import com.erp.pojo.StoreAlert;

import java.util.List;

/**
 * 库存预警 Mapper 接口
 *
 * @author baoyang
 */
public interface StoreAlertMapper {

    List<StoreAlert> findAll();

    List<StoreAlert> findAllWarm();
}