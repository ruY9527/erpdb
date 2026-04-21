package com.erp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.erp.mapper.GoodsMapper;
import com.erp.mapper.StoreMapper;
import com.erp.mapper.StoredetailMapper;
import com.erp.pojo.Goods;
import com.erp.pojo.Store;
import com.erp.pojo.Storedetail;
import com.erp.service.StoredetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 库存详情服务实现类
 *
 * @author baoyang
 */
@Service
public class StoredetailServiceImpl implements StoredetailService {

    @Autowired
    private StoredetailMapper storedetailMapper;

    @Autowired
    private StoreMapper storeMapper;

    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public List<Storedetail> list() {
        List<Storedetail> list = storedetailMapper.selectList(null);
        fillAssociationData(list);
        return list;
    }

    @Override
    public Page<Storedetail> page(Integer page, Integer rows, Long storeId, Long goodsId) {
        Page<Storedetail> pageObj = new Page<>(page, rows);
        LambdaQueryWrapper<Storedetail> wrapper = new LambdaQueryWrapper<>();

        if (storeId != null) {
            wrapper.eq(Storedetail::getStoreId, storeId);
        }
        if (goodsId != null) {
            wrapper.eq(Storedetail::getGoodsId, goodsId);
        }

        Page<Storedetail> result = storedetailMapper.selectPage(pageObj, wrapper);
        fillAssociationData(result.getRecords());
        return result;
    }

    @Override
    public Storedetail getById(Long id) {
        Storedetail storedetail = storedetailMapper.selectById(id);
        if (storedetail != null) {
            fillAssociationData(Collections.singletonList(storedetail));
        }
        return storedetail;
    }

    @Override
    public Map<String, Object> stats() {
        Map<String, Object> stats = new HashMap<>();

        LambdaQueryWrapper<Store> storeWrapper = new LambdaQueryWrapper<>();
        Long totalStores = storeMapper.selectCount(storeWrapper);

        LambdaQueryWrapper<Goods> goodsWrapper = new LambdaQueryWrapper<>();
        Long totalGoods = goodsMapper.selectCount(goodsWrapper);

        LambdaQueryWrapper<Storedetail> detailWrapper = new LambdaQueryWrapper<>();
        Long totalItems = storedetailMapper.selectCount(detailWrapper);

        stats.put("totalStores", totalStores);
        stats.put("totalGoods", totalGoods);
        stats.put("totalItems", totalItems);

        return stats;
    }

    private void fillAssociationData(List<Storedetail> list) {
        for (Storedetail sd : list) {
            if (sd.getStoreId() != null) {
                Store store = storeMapper.selectById(sd.getStoreId());
                sd.setStore(store);
            }
            if (sd.getGoodsId() != null) {
                Goods goods = goodsMapper.selectById(sd.getGoodsId());
                sd.setGoods(goods);
            }
        }
    }
}