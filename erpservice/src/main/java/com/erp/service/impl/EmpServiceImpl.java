package com.erp.service.impl;

import com.erp.mapper.EmpMapper;
import com.erp.service.EmpService;
import com.erp.utils.MyResult;
import com.github.pagehelper.PageHelper;
import com.yang.erp.pojo.Dept;
import com.yang.erp.pojo.Emp;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpMapper empMapper;

    /**
     * 获取全部的对象信息
     */
    @Override
    public List<Emp> findAll() {
        return empMapper.findAll();
    }

    //添加对象信息
    @Override
    public void addEmp(Emp emp) {
        emp.setState(Emp.USE);
        empMapper.addEmp(emp);
    }

    //根据id删除对象信息
    @Override
    public void deleteEmp(Long id) {
        empMapper.deleteEmp(id);
    }

    //根据对象信息获取(包括条件查询和)

    @Override
    public List<Emp> selectByEmp(Emp emp,Integer page,Integer rows) {
        PageHelper.startPage(page,rows);
        List<Emp> empList = empMapper.selectByEmp(emp);
        return empList;
    }

    //获取数据库中个数的数量

    @Override
    public Integer getCount() {
        return empMapper.getCount();
    }

    //根据id获取一个对象
    @Override
    public Emp getEmpById(Long id) {
        return empMapper.getEmpById(id);
    }

    //修改一个对象信息
    @Override
    public void updateEmp(Emp emp) {
        empMapper.updateEmp(emp);
    }

    //根据username和pwd来获取信息
    @Override
    public Emp findEmpByUsernameAndPwd(String username, String pwd) {
        //DigestUtils.md5DigestAsHex(pwd.getBytes());
        return empMapper.findEmpByUsernameAndPwd(username,pwd);
    }
    //确认旧密码是否存在
    @Override
    public Emp checkEmpByEidAndPwd(Long eid, String pwd) {
        return empMapper.checkEmpByEidAndPwd(eid,pwd);
    }

    //根据id来修改密码的操作
    @Override
    public void updatePwdByEid(Long eid, String pwd) {
        empMapper.updatePwdByEid(eid,pwd);
    }

    //获取全部信息
    @Override
    public List<Emp> selectAll()
    {
        return empMapper.selectAll();
    }

    //导出
    @Override
    public void export(Emp emp, OutputStream outputStream)
    {
        List<Emp> empList = empMapper.selectByEmp(emp);
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
        HSSFSheet sheet = hssfWorkbook.createSheet("员工");
        //设置头
        HSSFRow row = sheet.createRow(0);
        //定义好每一个头
        String [] headsName = {"登录名称","真实姓名","1男,0女","邮件地址","联系电话","联系地址","出生年月日"};

        //指定每列的宽度
        int [] columnsWidths = {4000,3000,3000,3000,3000,3000,3000};
        HSSFCell cell = null;
        for(int i=0;i<headsName.length;i++){
            cell =  row.createCell(i);
            cell.setCellValue(headsName[i]);
            sheet.setColumnWidth(i,columnsWidths[i]);
        }
        //导入数值
        int i = 1;
        for (Emp e:empList)
        {
            //"登录名称","真实姓名","1男,0女","邮件地址","联系电话","联系地址","出生年月日","1:使用,0禁用"
            row =  sheet.createRow(i);
            row.createCell(0).setCellValue(e.getUsername());
            row.createCell(1).setCellValue(e.getName());
            row.createCell(2).setCellValue(e.getGender());
            row.createCell(3).setCellValue(e.getEmail());
            row.createCell(4).setCellValue(e.getTele());
            row.createCell(5).setCellValue(e.getAddress());
            row.createCell(6).setCellValue(e.getBirthday());
            i++;
        }
        //写入流中
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

    //导入
    @Override
    public MyResult doImport(InputStream inputStream)
    {
        HSSFWorkbook hssfWorkbook = null;
        try
        {
            hssfWorkbook = new HSSFWorkbook(inputStream);
            HSSFSheet sheet = hssfWorkbook.getSheetAt(0);
            if(sheet.getSheetName().equals("员工")){
            } else {
                return new MyResult(false,"sheet名字有误,请已员工为名");
            }
            //获取最后一个
            int lastRowNum = sheet.getLastRowNum();
            Emp emp = null;
            for(int i=1;i<=lastRowNum;i++){
                emp = new Emp();
                //根据登录名来查询
                String username =  sheet.getRow(i).getCell(0).getStringCellValue();
                emp.setUsername(username);
                List<Emp> empList = empMapper.selectByEmp(emp);
                if(empList.size()>0){
                    emp = empList.get(0);
                }
                //"登录名称","真实姓名","1男,0女","邮件地址","联系电话","联系地址","出生年月日","1:使用,0禁用"
                emp.setName(sheet.getRow(i).getCell(1).getStringCellValue());
                emp.setGender(Integer.valueOf(sheet.getRow(i).getCell(2).getStringCellValue()));
                emp.setEmail(sheet.getRow(i).getCell(3).getStringCellValue());
                emp.setTele(sheet.getRow(i).getCell(4).getStringCellValue());
                emp.setAddress(sheet.getRow(i).getCell(5).getStringCellValue());
                emp.setBirthday(sheet.getRow(i).getCell(6).getStringCellValue());
                emp.setState(Emp.USE);
                //如果empList的长度为0，不存在的，插入数据库中
                if(empList.size() == 0){
                    empMapper.addEmp(emp);
                }
            }
            return new MyResult(true,"导入成功");
        } catch (IOException e)
        {
            e.printStackTrace();
            return new MyResult(false,"导入失败");
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
