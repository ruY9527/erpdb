package com.yang.erp.pojo;

public class Ordersdetail {
    private Long odid;
    private Long goodsId;
    private String goodsname;
    private Double price;
    private Long num;
    private Double money;
    private String endtime;
    private String ender;
    private Long storeId;
    private String state;
    private Long ordersId;
    private Orders orders;

    public static final String STATE_NOT_IN = "0";   //没有入库
    public static final String STATE_IN="1";         //已经入库


    public Long getOdid() {
        return odid;
    }

    public void setOdid(Long odid) {
        this.odid = odid;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsname() {
        return goodsname;
    }

    public void setGoodsname(String goodsname) {
        this.goodsname = goodsname;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Long getNum() {
        return num;
    }

    public void setNum(Long num) {
        this.num = num;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public String getEnder() {
        return ender;
    }

    public void setEnder(String ender) {
        this.ender = ender;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Long getOrdersId() {
        return ordersId;
    }

    public void setOrdersId(Long ordersId) {
        this.ordersId = ordersId;
    }

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "Ordersdetail{" +
                "odid=" + odid +
                ", goodsId=" + goodsId +
                ", goodsname='" + goodsname + '\'' +
                ", price=" + price +
                ", num=" + num +
                ", money=" + money +
                ", endtime='" + endtime + '\'' +
                ", ender='" + ender + '\'' +
                ", storeId='" + storeId + '\'' +
                ", state='" + state + '\'' +
                ", ordersId='" + ordersId + '\'' +
                ", orders=" + orders +
                '}';
    }
}
