package com.erp.service.impl;

import com.erp.mapper.StoreMapper;
import com.erp.service.StoreService;
import com.github.pagehelper.PageHelper;
import com.yang.erp.pojo.Store;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

@Service
public class StoreServiceImpl implements StoreService {

    @Autowired
    private StoreMapper storeMapper;

    //获取全部信息
    @Override
    public List<Store> findAll(Store store,Integer page,Integer rows) {
        if(page !=null && rows !=null){
            PageHelper.startPage(page,rows);
        }
        List<Store> storeList =  storeMapper.findAll(store);
        return storeList;
    }


    //添加
    @Override
    public void add(Store store)
    {
        store.setState(Store.USE);
        storeMapper.add(store);
    }

    //虚拟删除
    @Override
    public void delete(Long sid)
    {
        storeMapper.delete(sid);
    }

    //获取一个pojo便于修改
    @Override
    public Store getPojoById(Long sid)
    {
        return storeMapper.getPojoById(sid);
    }

    //修改
    @Override
    public void update(Store store)
    {
        storeMapper.update(store);
    }

    //无条件的获取全部信息
    @Override
    public List<Store> selectAll()
    {
        return storeMapper.selectAll();
    }

    //根据empId来获取信息
    @Override
    public List<Store> findByEmpId(Long empId) {
        return storeMapper.findByEmpId(empId);
    }

    //导出
    @Override
    public void export(Store store, OutputStream outputStream)
    {
        //根据条件查询
        List<Store> storeList = storeMapper.findAll(store);
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
        HSSFSheet sheet = hssfWorkbook.createSheet("仓库");
        //设置头
        HSSFRow row = sheet.createRow(0);
        //定义好每一个头
        String [] headsName = {"仓库名称","管理员名字"};
        //指定每列的宽度
        int [] columnsWidths = {4000,3000};
        HSSFCell cell = null;
        for(int i=0;i<headsName.length;i++){
            cell = row.createCell(i);
            cell.setCellValue(headsName[i]);
            sheet.setColumnWidth(i,columnsWidths[i]);
        }
        //设置内容
        int i = 1;
        for (Store s:storeList)
        {
           row = sheet.createRow(i);
           row.createCell(0).setCellValue(s.getName());
           row.createCell(1).setCellValue(s.getEmp().getName());
           i++;
        }
        //写入文件流中
        try
        {
            hssfWorkbook.write(outputStream);
        } catch (IOException e)
        {
            e.printStackTrace();
        } finally
        {
            try
            {
                hssfWorkbook.close();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
}
