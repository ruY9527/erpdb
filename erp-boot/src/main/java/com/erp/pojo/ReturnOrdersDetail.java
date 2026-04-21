package com.erp.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 退货订单详情
 *
 * @author baoyang
 */
@TableName("returnordersdetail")
public class ReturnOrdersDetail {

    public static final String ON_GO = "0";
    public static final String HAVE_GO = "1";

    @TableId("rosd")
    private Long rosd;

    @TableField("returnordersId")
    private Long returnordersId;

    @TableField("goodsId")
    private Long goodsId;

    private Long num;
    private Double price;
    private Double money;
    private String state;
    private String endtime;
    private Long ender;

    @TableField("storeId")
    private Long storeId;

    @TableField(exist = false)
    private ReturnOrders returnOrders;

    public Long getRosd() { return rosd; }
    public void setRosd(Long rosd) { this.rosd = rosd; }
    public Long getReturnordersId() { return returnordersId; }
    public void setReturnordersId(Long returnordersId) { this.returnordersId = returnordersId; }
    public Long getGoodsId() { return goodsId; }
    public void setGoodsId(Long goodsId) { this.goodsId = goodsId; }
    public Long getNum() { return num; }
    public void setNum(Long num) { this.num = num; }
    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }
    public Double getMoney() { return money; }
    public void setMoney(Double money) { this.money = money; }
    public String getState() { return state; }
    public void setState(String state) { this.state = state; }
    public String getEndtime() { return endtime; }
    public void setEndtime(String endtime) { this.endtime = endtime; }
    public Long getEnder() { return ender; }
    public void setEnder(Long ender) { this.ender = ender; }
    public Long getStoreId() { return storeId; }
    public void setStoreId(Long storeId) { this.storeId = storeId; }
    public ReturnOrders getReturnOrders() { return returnOrders; }
    public void setReturnOrders(ReturnOrders returnOrders) { this.returnOrders = returnOrders; }
}