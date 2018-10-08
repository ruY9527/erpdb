package com.erp.mapper;

import com.yang.erp.pojo.GoodsType;

import java.util.List;

public interface GoodsTypeMapper
{
    public List<GoodsType> findAll(GoodsType goodsType);

    public void add(String name);

    public void deleteGoodsTypeById(Long gid);

    public GoodsType findForUpdate(Long gid);

    public void updateGoodstype(GoodsType goodsType);

    public Integer getCount();

    public void addByGoodsType(GoodsType goodsType);

    public List<GoodsType> findGoodsType(GoodsType goodsType);

    public List<GoodsType> selectAll();
}
