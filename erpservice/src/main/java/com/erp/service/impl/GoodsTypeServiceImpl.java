package com.erp.service.impl;

import com.erp.mapper.GoodsTypeMapper;
import com.erp.service.GoodsTypeService;
import com.erp.utils.MyResult;
import com.github.pagehelper.PageHelper;
import com.yang.erp.pojo.GoodsType;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

@Service
public class GoodsTypeServiceImpl implements GoodsTypeService
{
    @Autowired
    private GoodsTypeMapper goodsTypeMapper;

    //查询全部或者根据条件查询
    @Override
    public List<GoodsType> findAll(GoodsType goodsType,Integer page,Integer rows)
    {
        if(page !=null && rows !=null){
            PageHelper.startPage(page,rows);
        }
        List<GoodsType> goodsTypeList = goodsTypeMapper.findAll(goodsType);
        return goodsTypeList;
    }

    //添加
    @Override
    public void add(String name)
    {
        goodsTypeMapper.add(name);
    }

    //删除
    @Override
    public void delete(Long gid)
    {
        goodsTypeMapper.deleteGoodsTypeById(gid);
    }

    //根据id获取便于修改
    @Override
    public GoodsType getPojoById(Long gid)
    {
        return goodsTypeMapper.findForUpdate(gid);
    }

    //修改
    @Override
    public void update(GoodsType goodsType)
    {
        goodsTypeMapper.updateGoodstype(goodsType);
    }

    //获取有效个数
    @Override
    public Integer getCount()
    {
        return goodsTypeMapper.getCount();
    }

    //直接获取全部
    @Override
    public List<GoodsType> selectAll()
    {
        return goodsTypeMapper.selectAll();
    }

    //导出操作
    @Override
    public void export(GoodsType goodsType, OutputStream outputStream)
    {
        //根据条件查询
        List<GoodsType> goodsTypes = goodsTypeMapper.findAll(goodsType);
        //创建excel工作本
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("商品类型");
        //获取头
        HSSFRow row = sheet.createRow(0);
        //定义好每一个头
        String [] headsName  = {"编号","名字"};
        int [] columnsWidths = {4000,8000};
        HSSFCell cell = null;
       for(int i=0;i<headsName.length;i++){
           cell = row.createCell(i);
           cell.setCellValue(headsName[i]);
           sheet.setColumnWidth(i,columnsWidths[i]);
       }
       //定义写入的内容
        int i =1;
        for (GoodsType goodsType1:goodsTypes)
        {
            row =  sheet.createRow(i);
            row.createCell(0).setCellValue(goodsType1.getGid());
            row.createCell(1).setCellValue(goodsType1.getName());
            i++;
        }
        //写入流中
        try
        {
            workbook.write(outputStream);
        } catch (IOException e)
        {
            e.printStackTrace();
        }finally
        {
            try
            {
                //关闭流
                workbook.close();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    //导入
    @Override
    public MyResult doImport(InputStream fileInputStream)
    {
        //创建exl
        HSSFWorkbook workbook = null;
        try
        {
            workbook = new HSSFWorkbook(fileInputStream);
            //获取ecl表名
            HSSFSheet sheet = workbook.getSheetAt(0);
            if(sheet.getSheetName().equals("商品类型")){
            } else {
                return new MyResult(false,"sheet名字有误,请已商品类型为名");
            }
            //获取最后一行个数
            int lastRowNum = sheet.getLastRowNum();
            GoodsType goodsType = null;
            for(int i=1;i<=lastRowNum;i++){
                goodsType = new GoodsType();
                //String gid =  sheet.getRow(i).getCell(0).getStringCellValue();  //商品类型id
                String name =  sheet.getRow(i).getCell(1).getStringCellValue();  //商品名字
                goodsType.setName(name);
                List<GoodsType> goodsTypes = goodsTypeMapper.findGoodsType(goodsType);
                if(goodsTypes.size()>0){
                    goodsType = goodsTypes.get(0);
                }
                if(goodsTypes.size() == 0){
                    goodsTypeMapper.addByGoodsType(goodsType);
                }
            }
            return new MyResult(true,"上传成功");
        } catch (IOException e)
        {
            e.printStackTrace();
            return new MyResult(false,"上传失败");
        }finally
        {
            try
            {
                //关闭流
                workbook.close();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
}
