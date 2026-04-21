package com.erp.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 商品实体
 *
 * @author baoyang
 */
@TableName("goods")
public class Goods {

    @TableId("gsid")
    private Long gsid;

    private String name;
    private String origin;
    private String producer;
    private String unit;
    private Double inprice;
    private Double outprice;

    @TableField("goodstypeId")
    private Long goodsTypeId;

    @TableField(exist = false)
    private GoodsType goodsType;

    private String state;

    public Long getGsid() { return gsid; }
    public void setGsid(Long gsid) { this.gsid = gsid; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getOrigin() { return origin; }
    public void setOrigin(String origin) { this.origin = origin; }
    public String getProducer() { return producer; }
    public void setProducer(String producer) { this.producer = producer; }
    public String getUnit() { return unit; }
    public void setUnit(String unit) { this.unit = unit; }
    public Double getInprice() { return inprice; }
    public void setInprice(Double inprice) { this.inprice = inprice; }
    public Double getOutprice() { return outprice; }
    public void setOutprice(Double outprice) { this.outprice = outprice; }
    public Long getGoodsTypeId() { return goodsTypeId; }
    public void setGoodsTypeId(Long goodsTypeId) { this.goodsTypeId = goodsTypeId; }
    public GoodsType getGoodsType() { return goodsType; }
    public void setGoodsType(GoodsType goodsType) { this.goodsType = goodsType; }
    public String getState() { return state; }
    public void setState(String state) { this.state = state; }
}