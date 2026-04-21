package com.erp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.erp.mapper.GoodsTypeMapper;
import com.erp.pojo.GoodsType;
import com.erp.service.GoodsTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商品类型服务实现类
 *
 * @author baoyang
 */
@Service
public class GoodsTypeServiceImpl implements GoodsTypeService {

    @Autowired
    private GoodsTypeMapper goodsTypeMapper;

    @Override
    public List<GoodsType> list() {
        LambdaQueryWrapper<GoodsType> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(GoodsType::getState, "1");
        return goodsTypeMapper.selectList(wrapper);
    }

    @Override
    public void add(GoodsType goodsType) {
        goodsType.setState("1");
        goodsTypeMapper.insert(goodsType);
    }

    @Override
    public void update(GoodsType goodsType) {
        goodsTypeMapper.updateById(goodsType);
    }

    @Override
    public void delete(Long id) {
        LambdaUpdateWrapper<GoodsType> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(GoodsType::getGid, id).set(GoodsType::getState, "0");
        goodsTypeMapper.update(null, wrapper);
    }

    @Override
    public GoodsType getById(Long id) {
        return goodsTypeMapper.selectById(id);
    }
}