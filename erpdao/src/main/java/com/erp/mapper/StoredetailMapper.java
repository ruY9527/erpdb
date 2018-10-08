package com.erp.mapper;

import com.yang.erp.pojo.Store;
import com.yang.erp.pojo.StoreDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StoredetailMapper {
    public void add(StoreDetail storeDetail);
    public StoreDetail findByGoogsId(@Param("goodsId") Long goodsId, @Param("storeId") Long storeId);
    public void updateNumBygoodsIdAndstoreId(StoreDetail detail);
    public StoreDetail findByGoodsIdAndStoreId(@Param("goodsId") Long goodsId,@Param("storeId") Long storeId);
    public List<StoreDetail> findAll(StoreDetail storeDetail);

    public List<Store> selectStoreByGoodsId(Long goodsId);
}
