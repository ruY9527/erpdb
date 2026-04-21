package com.erp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.erp.pojo.Store;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 仓库 Mapper 接口
 *
 * @author baoyang
 */
public interface StoreMapper extends BaseMapper<Store> {

    List<Store> findAll(Store store);

    List<Store> findByEmpId(Long empId);

    List<Map> findGoodsNameByStoreId(Long storeId);

    void add(Store store);

    void delete(Long sid);

    Store getPojoById(@Param("sid") Long sid);

    void update(Store store);

    List<Store> selectAll();
}