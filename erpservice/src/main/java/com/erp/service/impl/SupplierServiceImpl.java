package com.erp.service.impl;

import com.erp.mapper.SupplierMapper;
import com.erp.service.SupplierService;
import com.erp.utils.MyResult;
import com.github.pagehelper.PageHelper;
import com.yang.erp.pojo.Supplier;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

@Service
public class SupplierServiceImpl implements SupplierService
{

    @Autowired
    private SupplierMapper supplierMapper;


    //根据type来获取信息
    @Override
    public List<Supplier> findByType(String type)
    {
        return supplierMapper.findSupplierByType(type);
    }

    //添加
    @Override
    public void add(Supplier supplier)
    {
        supplierMapper.add(supplier);
    }

    //根据id获取对象,便于修改操作
    @Override
    public Supplier getPojoById(Long suid)
    {
        return supplierMapper.getPojoById(suid);
    }

    //修改操作
    @Override
    public void update(Supplier supplier)
    {
        supplierMapper.updateSupplier(supplier);
    }

    //删除,修改状态
    @Override
    public void delete(Long suid)
    {
        supplierMapper.deleteInUpdate(suid);
    }

    //根据Supplier查询
    @Override
    public List<Supplier> findBySupplier(Supplier supplier,Integer page,Integer rows)
    {
        PageHelper.startPage(page,rows);
        List<Supplier> bySupplier = supplierMapper.findBySupplier(supplier);
        return bySupplier;
    }

    //根据type来获取个数
    @Override
    public Integer selectCountByType(String type)
    {
        return supplierMapper.selectCountByType(type);
    }

    //导出的功能
    @Override
    public void export(Supplier supplier,OutputStream outputStream)
    {
        //根据条件查询
        List<Supplier> suppliers = supplierMapper.findBySupplier(supplier);
        //创建excel工作本
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
        HSSFSheet hssfSheet = null;
        //根据供应商中的类型来创建相应的工作名字
        if(supplier.getType().equals("1")){
            hssfSheet =  hssfWorkbook.createSheet("供应商");
        }
        if(supplier.getType().equals("2")){
            hssfSheet =  hssfWorkbook.createSheet("客户");
        }

        HSSFRow row = hssfSheet.createRow(0);
        //定义好每一个头
        String [] headsName = {"名称","地址","联系人","联系电话","邮件地址"};
        //指定每列的宽度
        int [] columnsWidths = {4000,8000,2000,3000,8000};
        HSSFCell cell = null;
        for(int i=0;i<headsName.length;i++){
            cell = row.createCell(i);
            cell.setCellValue(headsName[i]);
            hssfSheet.setColumnWidth(i,columnsWidths[i]);
        }

        int i =1;
        //写入内容
        for (Supplier s:suppliers)
        {
            row =  hssfSheet.createRow(i);
            row.createCell(0).setCellValue(s.getName());
            row.createCell(1).setCellValue(s.getAddress());
            row.createCell(2).setCellValue(s.getContact());
            row.createCell(3).setCellValue(s.getTele());
            row.createCell(4).setCellValue(s.getEmail());
            i++;
        }
        try
        {
            //写入流中
            hssfWorkbook.write(outputStream);
        } catch (IOException e)
        {
            e.printStackTrace();
        } finally
        {
            try
            {
                //关闭工作流
                hssfWorkbook.close();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    //导入文件
    @Override
    public MyResult doImport(InputStream fileInputStream)
    {
        HSSFWorkbook hssfWorkbook = null;
        try
        {
            hssfWorkbook = new HSSFWorkbook(fileInputStream);
            HSSFSheet sheet = hssfWorkbook.getSheetAt(0);
            String type = "";
            if("供应商".equals(sheet.getSheetName())){
                type = Supplier.TYPE_SUPPLIER;
            } else if ("客户".equals(sheet.getSheetName())){
                type = Supplier.TYPE_CUSTOMER;
            } else {
                return new MyResult(false,"sheet名字有误,请已供应商或者客户类型为名");
            }
            //获取最后一行的个数
            int lastRowNum = sheet.getLastRowNum();
            Supplier supplier = null;
            for(int i=1;i<lastRowNum;i++){
                supplier = new Supplier();
                String name =  sheet.getRow(i).getCell(0).getStringCellValue(); //供应商名字
                supplier.setName(name);
                List<Supplier> supplierList = supplierMapper.selectByNameAndType(name,type);
                if(supplierList.size()>0){
                    supplier = supplierList.get(0);
                }
                supplier.setAddress(sheet.getRow(i).getCell(1).getStringCellValue());
                supplier.setContact(sheet.getRow(i).getCell(2).getStringCellValue());
                supplier.setTele(sheet.getRow(i).getCell(3).getStringCellValue());
                supplier.setEmail(sheet.getRow(i).getCell(4).getStringCellValue());
                if(supplierList.size() == 0){
                    supplier.setType(type);
                    supplierMapper.add(supplier);
                }
            }

            return new MyResult(true,"上传成功");
        } catch (IOException e)
        {
            e.printStackTrace();
            return new MyResult(false,"出现异常");
        }finally
        {
            if(hssfWorkbook != null){
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


}
