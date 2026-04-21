package com.erp.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 退货订单
 *
 * @author baoyang
 */
@TableName("returnorders")
public class ReturnOrders {

    public static final String ON_CHECK = "0";
    public static final String HAVE_CHECK = "1";
    public static final String SALES_RETURN = "2";

    @TableId("roid")
    private Long roid;

    @TableField("ordersOid")
    private Long ordersOid;

    private String createtime;
    private Long createer;
    private Long checker;
    private String checktime;
    private Long ender;
    private String endtime;

    @TableField("supplierId")
    private Long supplierId;

    private Double totalmoney;
    private String type;
    private String state;

    @TableField(exist = false)
    private Emp createEmp;

    @TableField(exist = false)
    private Emp checkEmp;

    @TableField(exist = false)
    private Emp endEmp;

    @TableField(exist = false)
    private Supplier supplier;

    public Long getRoid() { return roid; }
    public void setRoid(Long roid) { this.roid = roid; }
    public Long getOrdersOid() { return ordersOid; }
    public void setOrdersOid(Long ordersOid) { this.ordersOid = ordersOid; }
    public String getCreatetime() { return createtime; }
    public void setCreatetime(String createtime) { this.createtime = createtime; }
    public Long getCreateer() { return createer; }
    public void setCreateer(Long createer) { this.createer = createer; }
    public Long getChecker() { return checker; }
    public void setChecker(Long checker) { this.checker = checker; }
    public String getChecktime() { return checktime; }
    public void setChecktime(String checktime) { this.checktime = checktime; }
    public Long getEnder() { return ender; }
    public void setEnder(Long ender) { this.ender = ender; }
    public String getEndtime() { return endtime; }
    public void setEndtime(String endtime) { this.endtime = endtime; }
    public Long getSupplierId() { return supplierId; }
    public void setSupplierId(Long supplierId) { this.supplierId = supplierId; }
    public Double getTotalmoney() { return totalmoney; }
    public void setTotalmoney(Double totalmoney) { this.totalmoney = totalmoney; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public String getState() { return state; }
    public void setState(String state) { this.state = state; }
    public Emp getCreateEmp() { return createEmp; }
    public void setCreateEmp(Emp createEmp) { this.createEmp = createEmp; }
    public Emp getCheckEmp() { return checkEmp; }
    public void setCheckEmp(Emp checkEmp) { this.checkEmp = checkEmp; }
    public Emp getEndEmp() { return endEmp; }
    public void setEndEmp(Emp endEmp) { this.endEmp = endEmp; }
    public Supplier getSupplier() { return supplier; }
    public void setSupplier(Supplier supplier) { this.supplier = supplier; }
}