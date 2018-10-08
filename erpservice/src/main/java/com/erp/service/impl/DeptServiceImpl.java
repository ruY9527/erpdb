package com.erp.service.impl;

import com.erp.mapper.DeptMapper;
import com.erp.service.DeptService;
import com.erp.utils.MyResult;
import com.github.pagehelper.PageHelper;
import com.yang.erp.pojo.Dept;
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
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptMapper deptMapper;

    //根据条件进行查询的操作
    @Override
    public List<Dept> selectDeptByNameOrTele(Dept dept,Integer page,Integer rows) {
        PageHelper.startPage(page,rows);
        return deptMapper.selectDeptByNameOrTele(dept);
    }

    //获取所有的个数的代码
    @Override
    public Integer getTotal() {
        return deptMapper.getTotal();
    }

    //添加操作
    @Override
    public void add(Dept dept) {
        deptMapper.add(dept);
    }

    //获取所有的信息
    @Override
    public List<Dept> findAll() {
        return deptMapper.findAll();
    }

    //根据id来删除信息
    @Override
    public void deleteDeptById(Long id) {
        deptMapper.deleteDeptById(id);
    }

    //根据id获取一个对象，便于修改的操作
    @Override
    public Dept getDeptById(Long id) {
        return deptMapper.getDeptById(id);
    }

    //修改对象方法
    @Override
    public void updateDept(Dept dept) {
        deptMapper.updtaeDept(dept);
    }

    //导出a
    @Override
    public void export(Dept dept, OutputStream outputStream)
    {
        //添加查询获取信息
        List<Dept> depts =  deptMapper.selectDeptByDept(dept);
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("部门");
        //设置头
        HSSFRow row = sheet.createRow(0);
        //定义好每一个头
        String [] headsName = {"名称","电话"};
        //指定每列的宽度
        int [] columnsWidths = {4000,5000};
        HSSFCell cell = null;
        for(int i=0;i<headsName.length;i++){
            cell = row.createCell(i);
            cell.setCellValue(headsName[i]);
            sheet.setColumnWidth(i,columnsWidths[i]);
        }

        //写入数据
        int i = 1;
        for(Dept dep:depts){
            row = sheet.createRow(i);
            row.createCell(0).setCellValue(dep.getName());
            row.createCell(1).setCellValue(dep.getTele());
            i++;
        }
        //写入流中
        try
        {
            workbook.write(outputStream);
        } catch (IOException e)
        {
            e.printStackTrace();
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

    //导入文件
    @Override
    public MyResult doImport(InputStream inputStream)
    {
        HSSFWorkbook workbook = null;
        try
        {
            workbook = new HSSFWorkbook(inputStream);
            HSSFSheet sheet = workbook.getSheetAt(0);
            if(sheet.getSheetName().equals("部门")){
            } else {
                return new MyResult(false,"sheet名字有误,请已部门为名");
            }
            //获取最后一行
            int lastRowNum = sheet.getLastRowNum();
            Dept dept = null;
            for(int i=1;i<=lastRowNum;i++){
                dept = new Dept();
                String name =  sheet.getRow(i).getCell(0).getStringCellValue();  //获取名字
                dept.setName(name);
                List<Dept> deptList = deptMapper.selectDeptByDept(dept);
                if(deptList.size()>0){
                    dept = deptList.get(0);
                }
                dept.setTele(sheet.getRow(i).getCell(1).getStringCellValue());
                if(deptList.size() == 0){
                    deptMapper.add(dept);
                }
            }
            return new MyResult(true,"导入成功");
        } catch (IOException e)
        {
            e.printStackTrace();
            return new MyResult(false,"导入失败");
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

}
