package com.erp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.erp.pojo.GoodsType;

import java.util.List;

/**
 * 商品类型 Mapper 接口
 *
 * @author baoyang
 */
public interface GoodsTypeMapper extends BaseMapper<GoodsType> {

    List<GoodsType> findAll(GoodsType goodsType);

    void add(String name);

    void deleteGoodsTypeById(Long gid);

    GoodsType findForUpdate(Long gid);

    void updateGoodstype(GoodsType goodsType);

    Integer getCount();

    void addByGoodsType(GoodsType goodsType);

    List<GoodsType> findGoodsType(GoodsType goodsType);

    List<GoodsType> selectAll();
}