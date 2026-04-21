package com.erp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.erp.pojo.Goods;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 商品 Mapper 接口
 *
 * @author baoyang
 */
public interface GoodsMapper extends BaseMapper<Goods> {

    List<Goods> findAll(Goods goods);

    void add(Goods goods);

    Goods getPojoById(Long gsid);

    void delete(Long gsid);

    void update(Goods goods);

    List<Goods> findAllBySalesReturn(String msg);

    Integer getCount();

    List<Goods> findByName(@Param("name") String name);
}