package com.erp.service;

import com.erp.utils.MyResult;
import com.yang.erp.pojo.Goods;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

public interface GoodsService {
    public List<Goods> findAll(Goods goods,Integer page,Integer rows);
    public void add(Goods goods);
    public Goods getPojoById(Long gsid);
    public void delete(Long gsid);
    public void update(Goods goods);
    public List<Goods> findAllBySalesReturn(String msg);
    public Integer getCount();

    public void export(Goods goods, OutputStream outputStream);

    public MyResult doImport(InputStream inputStream);
}
