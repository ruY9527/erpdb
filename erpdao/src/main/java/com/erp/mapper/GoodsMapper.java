package com.erp.mapper;

import com.yang.erp.pojo.Goods;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GoodsMapper {
    public List<Goods> findAll(Goods goods);
    public void add(Goods goods);
    public Goods getPojoById(Long gsid);
    public void delete(Long gsid);
    public void update(Goods goods);
    public List<Goods> findAllBySalesReturn(String msg);
    public Integer getCount();

    public List<Goods> findByName(@Param("name") String name);
}
