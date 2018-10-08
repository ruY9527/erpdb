package com.erp.service.impl;

import com.erp.mapper.WaybillMapper;
import com.erp.service.WaybillService;
import com.github.pagehelper.PageHelper;
import com.yang.erp.pojo.WayBill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class WaybillServiceImpl implements WaybillService
{

    @Autowired
    private WaybillMapper waybillMapper;

    //查询全部或者条件查询
    @Override
    public List<WayBill> findAll(WayBill wayBill, Integer page, Integer rows)
    {
        PageHelper.startPage(page,rows);
        List<WayBill> wayBills =  waybillMapper.findAll(wayBill);
        return wayBills;
    }

    //添加
    @Override
    public void add(WayBill wayBill)
    {
        wayBill.setCreatetime(getCurrentTime());
        waybillMapper.add(wayBill);
    }

    //根据sn获取一个信息
    @Override
    public WayBill getPojoById(Long sn)
    {
        return waybillMapper.getPojoById(sn);
    }

    //修改
    @Override
    public void update(WayBill wayBill)
    {
        waybillMapper.update(wayBill);
    }

    //虚拟删除操作
    @Override
    public void delete(Long sn)
    {
        waybillMapper.delete(sn);
    }

    @Override
    public Integer selectCount()
    {
        return waybillMapper.selectCount();
    }

    //获取当前时间
    private String getCurrentTime(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String format = simpleDateFormat.format(new Date());
        return format;
    }


}
