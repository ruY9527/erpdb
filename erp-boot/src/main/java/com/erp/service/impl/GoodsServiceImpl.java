package com.erp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.erp.mapper.GoodsMapper;
import com.erp.mapper.GoodsTypeMapper;
import com.erp.pojo.Goods;
import com.erp.pojo.GoodsType;
import com.erp.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * 商品服务实现类
 *
 * @author baoyang
 */
@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private GoodsTypeMapper goodsTypeMapper;

    @Override
    public List<Goods> list() {
        LambdaQueryWrapper<Goods> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Goods::getState, "1");
        List<Goods> list = goodsMapper.selectList(wrapper);
        fillGoodsType(list);
        return list;
    }

    @Override
    public Page<Goods> page(Integer page, Integer rows, String name) {
        Page<Goods> pageObj = new Page<>(page, rows);
        LambdaQueryWrapper<Goods> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Goods::getState, "1");

        if (name != null && !name.isEmpty()) {
            wrapper.like(Goods::getName, name);
        }

        Page<Goods> result = goodsMapper.selectPage(pageObj, wrapper);
        fillGoodsType(result.getRecords());
        return result;
    }

    @Override
    public void add(Goods goods) {
        goods.setState("1");
        goodsMapper.insert(goods);
    }

    @Override
    public void update(Goods goods) {
        goodsMapper.updateById(goods);
    }

    @Override
    public void delete(Long id) {
        LambdaUpdateWrapper<Goods> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(Goods::getGsid, id).set(Goods::getState, "0");
        goodsMapper.update(null, wrapper);
    }

    @Override
    public Goods getById(Long id) {
        Goods goods = goodsMapper.selectById(id);
        if (goods != null) {
            fillGoodsType(Collections.singletonList(goods));
        }
        return goods;
    }

    /**
 * 填充商品类型信息
 */
    private void fillGoodsType(List<Goods> goodsList) {
        for (Goods goods : goodsList) {
            if (goods.getGoodsTypeId() != null) {
                GoodsType goodsType = goodsTypeMapper.selectById(goods.getGoodsTypeId());
                goods.setGoodsType(goodsType);
            }
        }
    }
}