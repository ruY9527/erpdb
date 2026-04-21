package com.erp.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 运单详情
 *
 * @author baoyang
 */
@TableName("waybilldetail")
public class WayBillDetail {

    @TableId("wdid")
    private Long wdid;

    private String waybillsn;

    @TableField("goodsId")
    private Long goodsId;

    private Long num;

    @TableField(exist = false)
    private Goods goods;

    public Long getWdid() { return wdid; }
    public void setWdid(Long wdid) { this.wdid = wdid; }
    public String getWaybillsn() { return waybillsn; }
    public void setWaybillsn(String waybillsn) { this.waybillsn = waybillsn; }
    public Long getGoodsId() { return goodsId; }
    public void setGoodsId(Long goodsId) { this.goodsId = goodsId; }
    public Long getNum() { return num; }
    public void setNum(Long num) { this.num = num; }
    public Goods getGoods() { return goods; }
    public void setGoods(Goods goods) { this.goods = goods; }
}