package com.erp.service.impl;

import com.erp.mapper.OrdersMapper;
import com.erp.mapper.OrdersdetailMapper;
import com.erp.service.OrdersdetailService;
import com.yang.erp.pojo.Emp;
import com.yang.erp.pojo.Orders;
import com.yang.erp.pojo.Ordersdetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class OrdersdetailServiceImpl implements OrdersdetailService
{

    @Autowired
    private OrdersMapper ordersMapper;

    @Autowired
    private OrdersdetailMapper ordersdetailMapper;

    @Override
    public void add(Emp emp, String supplierId, List<Ordersdetail> ordersdetails, String type)
    {
        //添加进入Orders和Ordersdetail表同时
        //先将数据插入orders中,orders需要自动从ordersdetails获取信息手动封装
        Orders orders = new Orders();
        orders.setState(Orders.STATE_CREATE);  //新添加的都是刚刚创建的
        orders.setType(type);  //新添加的都是采购
        String ctime =  getTime();
        orders.setCreatetime(ctime); //创建日期
        orders.setSupplierId(Long.parseLong(supplierId));  //设置供应的id
        orders.setCreateer(emp.getEid());  //设置创建人id信息
        double totalMoney = 0;
        //总计金额
        for (Ordersdetail ordersdetail : ordersdetails)
        {
            totalMoney += ordersdetail.getPrice() * ordersdetail.getNum();
        }
        orders.setTotalmoney(totalMoney);
        ordersMapper.add(orders);
        //orders封装完成,遍历分别插入orderdetail信息
        for (Ordersdetail ordersdetail : ordersdetails)
        {
            ordersdetail.setOrdersId(orders.getOid());
            ordersdetail.setMoney(ordersdetail.getNum() * ordersdetail.getPrice());
            if (type.equals("1"))
            {
                //采购信息添加
                ordersdetail.setState(Ordersdetail.STATE_NOT_IN);
            }
            if(type.equals("2"))
            {
                ordersdetail.setState(Ordersdetail.STATE_NOT_IN);
            }

            ordersdetailMapper.add(ordersdetail);
        }

    }


    //获取当前时间的操作
    public String getTime(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String nowTime =  simpleDateFormat.format(new Date());
        return nowTime;
    }
}
