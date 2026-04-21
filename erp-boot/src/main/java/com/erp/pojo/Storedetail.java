package com.erp.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 库存详情
 *
 * @author baoyang
 */
@TableName("storedetail")
public class Storedetail {

    @TableId("sdid")
    private Long sdid;

    @TableField("storeId")
    private Long storeId;

    @TableField("goodsId")
    private Long goodsId;

    private Long num;

    @TableField(exist = false)
    private Store store;

    @TableField(exist = false)
    private Goods goods;

    public Long getSdid() { return sdid; }
    public void setSdid(Long sdid) { this.sdid = sdid; }
    public Long getStoreId() { return storeId; }
    public void setStoreId(Long storeId) { this.storeId = storeId; }
    public Long getGoodsId() { return goodsId; }
    public void setGoodsId(Long goodsId) { this.goodsId = goodsId; }
    public Long getNum() { return num; }
    public void setNum(Long num) { this.num = num; }
    public Store getStore() { return store; }
    public void setStore(Store store) { this.store = store; }
    public Goods getGoods() { return goods; }
    public void setGoods(Goods goods) { this.goods = goods; }
}