package com.erp.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 仓库操作记录
 *
 * @author baoyang
 */
@TableName("storeoper")
public class StoreOper {

    public static final String TYPE_IN = "1";
    public static final String TYPE_OUT = "2";

    @TableId("soid")
    private Long soid;

    @TableField("empId")
    private Long empId;

    private String opertime;

    @TableField("storeId")
    private Long storeId;

    @TableField("goodsId")
    private Long goodsId;

    private Long num;
    private String type;

    public Long getSoid() { return soid; }
    public void setSoid(Long soid) { this.soid = soid; }
    public Long getEmpId() { return empId; }
    public void setEmpId(Long empId) { this.empId = empId; }
    public String getOpertime() { return opertime; }
    public void setOpertime(String opertime) { this.opertime = opertime; }
    public Long getStoreId() { return storeId; }
    public void setStoreId(Long storeId) { this.storeId = storeId; }
    public Long getGoodsId() { return goodsId; }
    public void setGoodsId(Long goodsId) { this.goodsId = goodsId; }
    public Long getNum() { return num; }
    public void setNum(Long num) { this.num = num; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
}