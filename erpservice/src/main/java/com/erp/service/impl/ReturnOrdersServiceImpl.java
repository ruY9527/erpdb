package com.erp.service.impl;

import com.erp.mapper.*;
import com.erp.service.ReturnOrdersService;
import com.erp.utils.MyResult;
import com.yang.erp.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.SimpleFormatter;

@Service
public class ReturnOrdersServiceImpl implements ReturnOrdersService
{

    @Autowired
    private ReturnOrdersMapper returnOrdersMapper;

    @Autowired
    private ReturnOrdersDetailMapper returnOrdersDetailMapper;

    @Autowired
    private EmpMapper empMapper;

    @Autowired
    private SupplierMapper supplierMapper;

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private StoreMapper storeMapper;

    @Autowired
    private StoredetailMapper storedetailMapper;

    @Autowired
    private StoreoperMapper storeoperMapper;


    //添加操作
    @Override
    public MyResult add(Long eid, String type, String supplierId, List<ReturnOrdersDetail> returnOrdersDetailList)
    {
        ReturnOrders returnOrders = null;
        Map map = null;
        double totalMoney = 0;
        for (ReturnOrdersDetail returnOrdersDetail:returnOrdersDetailList)
        {
            //根据supplierId和goodsId来查询出原订单信息
            map = returnOrdersMapper.selectOldOidBySupplierIdAndGoodsId(supplierId, returnOrdersDetail.getGoodsId());
            //对个数做判断
            if ((Long) map.get("num") < returnOrdersDetail.getNum())
            {
                return new MyResult(false, "你输入的个数大于库存个数");
            } else {
                totalMoney += returnOrdersDetail.getPrice() * returnOrdersDetail.getNum();
            }
        }
            //添加退货订单信息,封装其属性
            returnOrders = new ReturnOrders();
            returnOrders.setSupplierId(Long.valueOf(supplierId));
            returnOrders.setOrdersOid((Long)map.get("oid"));
            returnOrders.setCreatetime(getCurrentTime());
            returnOrders.setCreateer(eid);
            returnOrders.setType(type);
            returnOrders.setTotalmoney(totalMoney);
            returnOrders.setState(ReturnOrders.ON_CHECK);
            returnOrdersMapper.add(returnOrders);

        for (ReturnOrdersDetail returnOrdersDetail:returnOrdersDetailList)
        {
            //同时添加订单退货详细信息,封装属性
            returnOrdersDetail.setReturnordersId(returnOrders.getRoid());
            returnOrdersDetail.setMoney(returnOrdersDetail.getPrice() * returnOrdersDetail.getNum());
            returnOrdersDetail.setState(ReturnOrdersDetail.ON_GO);
            returnOrdersDetailMapper.add(returnOrdersDetail);
        }
        return new MyResult(true,"成功");
    }


    //查询全部或者条件查询
    @Override
    public List<ReturnOrders> findByType(String type, String state)
    {
        List<ReturnOrders> returnOrdersList =  returnOrdersMapper.findByType(type,state);
        if(returnOrdersList !=null && returnOrdersList.size()>0){
            getEveryEmp(returnOrdersList);
        }
        return returnOrdersList;
    }

    //查询我的信息(当前登录用户信息)
    @Override
    public List<ReturnOrders> myFindByType(Long eid, String type)
    {
        List<ReturnOrders> returnOrdersList =  returnOrdersMapper.myFindByType(eid,type);
        if(returnOrdersList !=null && returnOrdersList.size()>0){
            getEveryEmp(returnOrdersList);
        }
        return returnOrdersList;
    }

    //审核业务
    @Override
    public MyResult doCheck(Long eid, Long roid)
    {
        //先根据eid获取信息
        ReturnOrders returnOrders =  returnOrdersMapper.getPojoByRoid(roid);
        if(returnOrders == null){
            return new MyResult(false,"没有此信息");
        }
        returnOrders.setChecker(eid);
        returnOrders.setChecktime(getCurrentTime());
        returnOrders.setState(ReturnOrders.HAVE_CHECK);
        //修改审核信息
        returnOrdersMapper.doCheckByUpdate(returnOrders);
        return new MyResult(true,"审核成功");
    }


    //出库的操作
    @Override
    public MyResult outBank(Long rosd, Long storeId,Long eid)
    {
        //获取storeId仓库中的信息
        List<Map> maps =  storeMapper.findGoodsNameByStoreId(storeId);
        //获取要出库，退货详细订单的pojo
        ReturnOrdersDetail returnOrdersDetail =  returnOrdersDetailMapper.selectPojoByRosd(rosd);
        //定义一个布尔类型的变量
        boolean flag = false;
        for (Map map:maps)
        {
            if (map.get("name").equals(returnOrdersDetail.getGoodsName()))
            {
                if (returnOrdersDetail.getNum() > (Long) map.get("num"))
                {
                    return new MyResult(false, "仓库中数量不足，请联系管理员");
                } else
                {
                    flag = true;
                }
            }
        }
        //如果flag是false,该仓库中没有
        if(flag==false){
            //该仓库中没有,根据goodsId去查询其他仓库是否有
                List<Store> storeList =  storedetailMapper.selectStoreByGoodsId(returnOrdersDetail.getGoodsId());
                StringBuffer havaGoodsName = new StringBuffer();
                if(storeList !=null && storeList.size()>0){
                    for (Store store:storeList)
                    {
                        havaGoodsName.append(store.getName());
                    }
                }
                String toString = havaGoodsName.toString();
                return new MyResult(false,"该仓库没有,请去"+toString+"中看");
        }

            //以上条件都成立,修改returnorders中状态,returnorderdetail中信息,
            returnOrdersDetail.setEndtime(getCurrentTime());
            returnOrdersDetail.setEnder(eid);
            returnOrdersDetail.setStoreId(storeId);
            returnOrdersDetail.setState(ReturnOrdersDetail.HAVA_GO);
            returnOrdersDetailMapper.updateOutBank(returnOrdersDetail);
            //间去store中的数量,修改storedetail数量,storeoper操作记录
            StoreDetail googsId = storedetailMapper.findByGoogsId(returnOrdersDetail.getGoodsId(), storeId);
            googsId.setNum(googsId.getNum() - returnOrdersDetail.getNum());
            storedetailMapper.updateNumBygoodsIdAndstoreId(googsId);
            //对仓库修改记录的操作,出库的操作
            StoreOper storeOper = new StoreOper();
            storeOper.setEmpId(eid);
            storeOper.setOpertime(getCurrentDateTime());
            storeOper.setStoreId(storeId);
            storeOper.setGoodsId(returnOrdersDetail.getGoodsId());
            storeOper.setNum(returnOrdersDetail.getNum());
            storeOper.setType(StoreOper.TYPE_OUT);
            //添加storeoper记录操作
            storeoperMapper.add(storeOper);
            //对returnordersdetail中查询是否全部出库,如果是,则修改returnorders中的sate状态
            Integer countNumers =  returnOrdersDetailMapper.selectCountByReturnOrdersId(returnOrdersDetail.getReturnordersId());
            if(countNumers == 0){
                //全部出库,修改returnOrdersDetail.getReturnordersId()中的状态
                ReturnOrders returnOrders = returnOrdersDetail.getReturnOrders();
                returnOrders.setEndtime(getCurrentTime());
                returnOrders.setEnder(eid);
                returnOrders.setState(ReturnOrders.SALRS_RETURN);
                returnOrdersMapper.outBank(returnOrders);
            }
        return new MyResult(true,"出库成功");
    }

    //获取当前时间的格式
    private String getCurrentTime(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String format = simpleDateFormat.format(new Date());
        return format;
    }

    //获取时间到分秒
    private String getCurrentDateTime(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String format = simpleDateFormat.format(new Date());
        return format;
    }

    //遍历当前人员的关系
    private void getEveryEmp(List<ReturnOrders> returnOrdersList){
        for (ReturnOrders returnOrder:returnOrdersList)
        {
            if(returnOrder.getCreateer() !=null){
                Emp createEmp = empMapper.getEmpById(returnOrder.getCreateer());
                returnOrder.setCreateEmp(createEmp);
            }
            if(returnOrder.getChecker() !=null){
                Emp checkEmp = empMapper.getEmpById(returnOrder.getChecker());
                returnOrder.setCheckEmp(checkEmp);
            }
            if(returnOrder.getEnder() !=null){
                Emp endEmp = empMapper.getEmpById(returnOrder.getEnder());
                returnOrder.setEndEmp(endEmp);
            }
            if(returnOrder.getSupplierId() !=null){
                Supplier supplier = supplierMapper.getPojoById(returnOrder.getSupplierId());
                returnOrder.setSupplier(supplier);
            }
        }
    }

}
