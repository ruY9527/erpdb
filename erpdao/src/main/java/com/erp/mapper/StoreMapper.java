package com.erp.mapper;

import com.yang.erp.pojo.Store;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface StoreMapper {

    public List<Store> findAll(Store store);
    public List<Store> findByEmpId(Long empId);
    public List<Map> findGoodsNameByStoreId(Long storeId);

    public void add(Store store);
    public void delete(Long sid);
    public Store getPojoById(@Param("sid") Long sid);
    public void update(Store store);

    public List<Store> selectAll();
}
