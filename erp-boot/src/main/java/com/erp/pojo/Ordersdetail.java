package com.erp.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 订单详情
 *
 * @author baoyang
 */
@TableName("ordersdetail")
public class Ordersdetail {

    public static final String STATE_NOT_IN = "0";
    public static final String STATE_IN = "1";

    @TableId("odid")
    private Long odid;

    @TableField("ordersId")
    private Long ordersId;

    @TableField("goodsId")
    private Long goodsId;

    private Long num;
    private Double price;
    private Double money;
    private String state;
    private String endtime;
    private String ender;

    @TableField("storeId")
    private Long storeId;

    @TableField(exist = false)
    private Goods goods;

    public Long getOdid() { return odid; }
    public void setOdid(Long odid) { this.odid = odid; }
    public Long getOrdersId() { return ordersId; }
    public void setOrdersId(Long ordersId) { this.ordersId = ordersId; }
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
    public String getEnder() { return ender; }
    public void setEnder(String ender) { this.ender = ender; }
    public Long getStoreId() { return storeId; }
    public void setStoreId(Long storeId) { this.storeId = storeId; }
    public Goods getGoods() { return goods; }
    public void setGoods(Goods goods) { this.goods = goods; }
}