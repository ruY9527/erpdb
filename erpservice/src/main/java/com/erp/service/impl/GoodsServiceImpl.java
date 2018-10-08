package com.erp.service.impl;

import com.erp.mapper.GoodsMapper;
import com.erp.mapper.GoodsTypeMapper;
import com.erp.service.GoodsService;
import com.erp.utils.MyResult;
import com.github.pagehelper.PageHelper;
import com.yang.erp.pojo.Goods;
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
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private GoodsTypeMapper goodsTypeMapper;

    //获取全部信息
    @Override
    public List<Goods> findAll(Goods goods,Integer page,Integer rows) {
        if(page != null && rows !=null){
            PageHelper.startPage(page,rows);
        }
        List<Goods> goodsList = goodsMapper.findAll(goods);
        return goodsList;
    }

    //添加
    @Override
    public void add(Goods goods)
    {
        goodsMapper.add(goods);
    }

    //根据gsid获取一个对象信息
    @Override
    public Goods getPojoById(Long gsid)
    {
        return goodsMapper.getPojoById(gsid);
    }

    //虚拟删除根据gsid
    @Override
    public void delete(Long gsid)
    {
        goodsMapper.delete(gsid);
    }

    //更新操作
    @Override
    public void update(Goods goods)
    {
        goodsMapper.update(goods);
    }

    //查询已经入库的商品
    @Override
    public List<Goods> findAllBySalesReturn(String msg)
    {
        return goodsMapper.findAllBySalesReturn(msg);
    }

    //获取有效的个数
    @Override
    public Integer getCount()
    {
        return goodsMapper.getCount();
    }

    //导出操作
    @Override
    public void export(Goods goods, OutputStream outputStream)
    {
        //根据条件来查询
        List<Goods> goodsList = goodsMapper.findAll(goods);
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("商品管理");
        //获取头
        HSSFRow row = sheet.createRow(0);
        //定义好每一个头
        String [] headsName = {"名称","产地","厂商","计量单位","进价","出价","状态(1:使用,0:停用)"};
        //指定每列的宽度
        int [] columnsWidths = {4000,8000,2000,3000,8000,3000,3000};
        HSSFCell cell = null;
        for(int i=0;i<headsName.length;i++){
            cell = row.createCell(i);
            cell.setCellValue(headsName[i]);
            sheet.setColumnWidth(i,columnsWidths[i]);
        }
        //写入内容
        int i = 1;
        for (Goods good:goodsList)
        {
            row =  sheet.createRow(i);
            row.createCell(0).setCellValue(good.getName());
            row.createCell(1).setCellValue(good.getOrigin());
            row.createCell(2).setCellValue(good.getProducer());
            row.createCell(3).setCellValue(good.getUnit());
            row.createCell(4).setCellValue(good.getInprice());
            row.createCell(5).setCellValue(good.getOutprice());
            row.createCell(6).setCellValue(good.getState());
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
                workbook.close();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    //导入
    @Override
    public MyResult doImport(InputStream inputStream)
    {
        HSSFWorkbook workbook = null;
        try
        {
            workbook = new HSSFWorkbook(inputStream);
            HSSFSheet sheet = workbook.getSheetAt(0);
            if(sheet.getSheetName().equals("商品管理")){
            }else {
                return new MyResult(false,"sheet名字有误,请已商品管理为名");
            }
            int lastRowNum = sheet.getLastRowNum();
            Goods goods = null;
            for(int i=1;i<=lastRowNum;i++){
                goods = new Goods();
                String name = sheet.getRow(i).getCell(0).getStringCellValue();
                goods.setName(name);
                //根据名字查询
                List<Goods> list = goodsMapper.findByName(name);
                if(list.size() > 0){
                    goods = list.get(0);
                }
                //封装进去
                goods.setOrigin(sheet.getRow(i).getCell(1).getStringCellValue());
                goods.setProducer(sheet.getRow(i).getCell(2).getStringCellValue());
                goods.setUnit(sheet.getRow(i).getCell(3).getStringCellValue());
                goods.setInprice(Double.valueOf(sheet.getRow(i).getCell(4).getNumericCellValue()));
                goods.setOutprice(Double.valueOf(sheet.getRow(i).getCell(5).getNumericCellValue()));
                goods.setState(sheet.getRow(i).getCell(6).getStringCellValue());
                if(list.size() == 0){
                    goodsMapper.add(goods);
                }
            }
            return new MyResult(true,"上传成功");
        } catch (IOException e)
        {
            e.printStackTrace();
            return new MyResult(false,"上传失败");
        } finally
        {
            try
            {
                workbook.close();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

}
