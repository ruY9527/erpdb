package com.yang.erp.pojo;

public class ReturnOrdersDetail
{
    public static final String ON_GO = "0";  //未出库
    public static final String HAVA_GO = "1";  //已出库

    private Long rosd;  //退货订单详细
    private Long goodsId; //商品id
    private String goodsName;  //商品名字
    private Double price;  //商品价格
    private Long num;  //商品数量
    private Double money;  //金额
    private String endtime;  //结束日期
    private Long ender;  //结束人id
    private Long storeId;  //仓库编号
    private String state;  //采购状态
    private Long returnordersId;  //退货订单id
    private ReturnOrders returnOrders;

    public Long getRosd()
    {
        return rosd;
    }

    public void setRosd(Long rosd)
    {
        this.rosd = rosd;
    }

    public Long getGoodsId()
    {
        return goodsId;
    }

    public void setGoodsId(Long goodsId)
    {
        this.goodsId = goodsId;
    }

    public String getGoodsName()
    {
        return goodsName;
    }

    public void setGoodsName(String goodsName)
    {
        this.goodsName = goodsName;
    }

    public Double getPrice()
    {
        return price;
    }

    public void setPrice(Double price)
    {
        this.price = price;
    }

    public Long getNum()
    {
        return num;
    }

    public void setNum(Long num)
    {
        this.num = num;
    }

    public String getEndtime()
    {
        return endtime;
    }

    public void setEndtime(String endtime)
    {
        this.endtime = endtime;
    }

    public Long getEnder()
    {
        return ender;
    }

    public void setEnder(Long ender)
    {
        this.ender = ender;
    }

    public Long getStoreId()
    {
        return storeId;
    }

    public void setStoreId(Long storeId)
    {
        this.storeId = storeId;
    }

    public String getState()
    {
        return state;
    }

    public void setState(String state)
    {
        this.state = state;
    }

    public Long getReturnordersId()
    {
        return returnordersId;
    }

    public void setReturnordersId(Long returnordersId)
    {
        this.returnordersId = returnordersId;
    }

    public Double getMoney()
    {
        return money;
    }

    public void setMoney(Double money)
    {
        this.money = money;
    }

    public ReturnOrders getReturnOrders()
    {
        return returnOrders;
    }

    public void setReturnOrders(ReturnOrders returnOrders)
    {
        this.returnOrders = returnOrders;
    }

    @Override
    public String toString()
    {
        return "ReturnOrdersDetail{" +
                "rosd=" + rosd +
                ", goodsId=" + goodsId +
                ", goodsName='" + goodsName + '\'' +
                ", price=" + price +
                ", num=" + num +
                ", money=" + money +
                ", endtime='" + endtime + '\'' +
                ", ender=" + ender +
                ", storeId=" + storeId +
                ", state='" + state + '\'' +
                ", returnordersId=" + returnordersId +
                ", returnOrders=" + returnOrders +
                '}';
    }
}
