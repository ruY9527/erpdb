package com.erp.service;

import com.erp.utils.MyResult;
import com.yang.erp.pojo.GoodsType;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

public interface GoodsTypeService
{
    public List<GoodsType> findAll(GoodsType goodsType,Integer page,Integer rows);

    public void add(String name);

    public void delete(Long gid);

    public GoodsType getPojoById(Long gid);

    public void update(GoodsType goodsType);

    public Integer getCount();

    public void export(GoodsType goodsType, OutputStream outputStream);

    public MyResult doImport(InputStream inputStream);

    public List<GoodsType> selectAll();
}
