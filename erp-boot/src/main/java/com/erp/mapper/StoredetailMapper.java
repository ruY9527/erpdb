package com.erp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.erp.pojo.Storedetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 库存详情Mapper
 *
 * @author baoyang
 */
public interface StoredetailMapper extends BaseMapper<Storedetail> {

    List<Storedetail> findAll(Storedetail storedetail);

    void add(Storedetail storedetail);

    Storedetail getPojoById(Long uuid);

    void delete(Long uuid);

    void update(Storedetail storedetail);

    Integer getCount();

    Storedetail findByGoodsId(@Param("goodsId") Long goodsId, @Param("storeId") Long storeId);

    void updateNumBygoodsIdAndstoreId(Storedetail storedetail);

    Storedetail findByGoodsIdAndStoreId(@Param("goodsId") Long goodsId, @Param("storeId") Long storeId);
}