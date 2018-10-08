package com.erp.service.impl;

import com.erp.mapper.*;
import com.erp.service.StoreOperService;
import com.erp.service.StoreService;
import com.erp.utils.MyResult;
import com.github.pagehelper.PageHelper;
import com.yang.erp.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class StoreOperServiceImpl implements StoreOperService
{

    //注入订单详细表单
    @Autowired
    private OrdersdetailMapper ordersdetailMapper;

    @Autowired
    private EmpMapper empMapper;

    @Autowired
    private StoredetailMapper storedetailMapper;

    @Autowired
    private StoreoperMapper storeoperMapper;

    @Autowired
    private OrdersMapper ordersMapper;


    @Override
    public void addAndUpdate(Long eid, Long odid, Long storeId)
    {
        //根据odid查询出信息
        Ordersdetail ordersdetail = ordersdetailMapper.findByOid(odid);
        //获取登陆人信息名字
        Emp emp = empMapper.getEmpById(eid);
        String nowTime =  getTime();
        //ordersdetail处理
        ordersdetail.setEndtime(nowTime);
        ordersdetail.setEnder(emp.getName());
        ordersdetail.setStoreId(storeId);
        ordersdetail.setState(Ordersdetail.STATE_IN);
        //更新的操作
        ordersdetailMapper.updateByOrderDetail(ordersdetail);

        //在StoreDetail添加之前判断是否已经存在
        StoreDetail detail = storedetailMapper.findByGoogsId(ordersdetail.getGoodsId(), storeId);
        if (detail != null)
        {
            //已经存在
            detail.setNum(detail.getNum() + ordersdetail.getNum());
            //更新数据库
            storedetailMapper.updateNumBygoodsIdAndstoreId(detail);
        } else
        {
            detail = new StoreDetail();
            //不存在
            //插入到仓库详细表中
            detail.setStoreId(storeId);
            detail.setGoodsId(ordersdetail.getGoodsId());
            detail.setNum(ordersdetail.getNum());
            //先更新ordersdetail详细信息
            //添加数据加入
            storedetailMapper.add(detail);
        }

        //插入到仓库记录表中
        StoreOper storeOper = new StoreOper();
        storeOper.setEmpId(eid);
        storeOper.setOpertime(nowTime);
        storeOper.setStoreId(storeId);

        storeOper.setGoodsId(ordersdetail.getGoodsId());
        storeOper.setNum(detail.getNum());
        storeOper.setType(StoreOper.TYPE_IN);
        //添加进入
        storeoperMapper.add(storeOper);
        //检查ordersdetail是否全部入库进去
        Long ordersId = ordersdetail.getOrdersId();
        Long andState = ordersdetailMapper.selectCountByOrdersIdAndState(ordersId);
        if(andState == 0){
            //全部入库
            ordersMapper.updateAddStoreState(eid,ordersId);
        }
    }


    //销售出库操作
    @Override
    public MyResult out(Long odid, Long storeId, Long eid)
    {
        //根据odid获取信息
        Ordersdetail ordersdetail = ordersdetailMapper.findByOid(odid);
        if(!ordersdetail.getState().equals("0")){
            return new MyResult(false,"已经出库了!");
        }
        //获取emp
        Emp emp = empMapper.getEmpById(eid);


        //根据goodsId和storeId来获取数量信息
        StoreDetail storeDetail = storedetailMapper.findByGoodsIdAndStoreId(ordersdetail.getGoodsId(),storeId);
        if(storeDetail == null){
            return  new MyResult(false,"此库中没此商品信息");
        }
        long num =  storeDetail.getNum().longValue() - ordersdetail.getNum().longValue();
        if (num>0){
            //库存充足
            storeDetail.setNum(num);
            //修改库中数量信息
            storedetailMapper.updateNumBygoodsIdAndstoreId(storeDetail);
        } else {
            return new MyResult(false,"仓库中数量不足");
        }

        //修改ordersdetail信息
        ordersdetail.setOdid(odid);
        ordersdetail.setEnder(emp.getName());
        ordersdetail.setEndtime(new Date().toString());
        ordersdetail.setStoreId(storeId);
        ordersdetail.setState("1");
        try{
            ordersdetailMapper.updateOutByOrdersdetail(ordersdetail);
        }catch (Exception e){
            e.printStackTrace();
            return new MyResult(false,"修改失败");
        }
        String nowTime =  getTime();
        //修改ordersdetail信息
        ordersdetail.setOdid(odid);
        ordersdetail.setEnder(emp.getName());
        ordersdetail.setEndtime(nowTime);
        ordersdetail.setStoreId(storeId);
        ordersdetail.setState("1");
        try{
            ordersdetailMapper.updateOutByOrdersdetail(ordersdetail);
        }catch (Exception e){
            e.printStackTrace();
            return new MyResult(false,"修改失败");
        }
        //对库存修改做记录
        StoreOper storeOper = new StoreOper();
        storeOper.setEmpId(eid);
        storeOper.setGoodsId(ordersdetail.getGoodsId());
        storeOper.setNum(num);
        storeOper.setStoreId(storeId);
        storeOper.setOpertime(new Date().toString());
        storeOper.setType("2");
        //插入数据库中
        storeoperMapper.add(storeOper);

        //检查订单下的是否都出库了
        Long ordersId =  ordersdetail.getOrdersId();
        Long count =  ordersdetailMapper.selectCountByOrdersIdAndState(ordersId);
        if(count == 0){
            //订单详细都已出库,修改订单的状态
            Orders orders =  ordersMapper.findForUpdateByOid(ordersId);
            if(orders !=null){
                orders.setEndtime(new Date().toString());
                orders.setEnder(eid);
                orders.setState("1");
                ordersMapper.updateOutByOrders(orders);
            }
        }
        return new MyResult(true,"出库成功");
    }

    //获取全部的操作记录
    @Override
    public List<StoreOper> findAll(StoreOper storeOper,Integer page,Integer rows)
    {
        PageHelper.startPage(page,rows);
        List<StoreOper> storeOperList = storeoperMapper.findAll(storeOper);
        return storeOperList;
    }

    //获取总个数
    @Override
    public Integer selectCountAll()
    {
        return storeoperMapper.selectCountAll();
    }

    //获取当前时间的操作
    public String getTime(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String nowTime =  simpleDateFormat.format(new Date());
        return nowTime;
    }
}
