package com.erp.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.List;

/**
 * 订单实体
 *
 * @author baoyang
 */
@TableName("orders")
public class Orders {

    public static final String STATE_CREATE = "0";
    public static final String STATE_CHECK = "1";
    public static final String STATE_START = "2";
    public static final String STATE_END = "3";

    public static final String TYPE_IN = "1";
    public static final String TYPE_OUT = "2";

    @TableId("oid")
    private Long oid;

    private String createtime;
    private String checktime;
    private String starttime;
    private String endtime;
    private String type;
    private Long createer;
    private Long checker;
    private Long starter;
    private Long ender;

    @TableField("supplierId")
    private Long supplierId;

    private Double totalmoney;
    private String state;
    private String waybillsn;

    @TableField(exist = false)
    private Emp emp;

    @TableField(exist = false)
    private Emp emp2;

    @TableField(exist = false)
    private Emp emp3;

    @TableField(exist = false)
    private Emp emp4;

    @TableField(exist = false)
    private Supplier supplier;

    @TableField(exist = false)
    private List<Ordersdetail> ordersdetailList;

    public Long getOid() { return oid; }
    public void setOid(Long oid) { this.oid = oid; }
    public String getCreatetime() { return createtime; }
    public void setCreatetime(String createtime) { this.createtime = createtime; }
    public String getChecktime() { return checktime; }
    public void setChecktime(String checktime) { this.checktime = checktime; }
    public String getStarttime() { return starttime; }
    public void setStarttime(String starttime) { this.starttime = starttime; }
    public String getEndtime() { return endtime; }
    public void setEndtime(String endtime) { this.endtime = endtime; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public Long getCreateer() { return createer; }
    public void setCreateer(Long createer) { this.createer = createer; }
    public Long getChecker() { return checker; }
    public void setChecker(Long checker) { this.checker = checker; }
    public Long getStarter() { return starter; }
    public void setStarter(Long starter) { this.starter = starter; }
    public Long getEnder() { return ender; }
    public void setEnder(Long ender) { this.ender = ender; }
    public Long getSupplierId() { return supplierId; }
    public void setSupplierId(Long supplierId) { this.supplierId = supplierId; }
    public Double getTotalmoney() { return totalmoney; }
    public void setTotalmoney(Double totalmoney) { this.totalmoney = totalmoney; }
    public String getState() { return state; }
    public void setState(String state) { this.state = state; }
    public String getWaybillsn() { return waybillsn; }
    public void setWaybillsn(String waybillsn) { this.waybillsn = waybillsn; }
    public Emp getEmp() { return emp; }
    public void setEmp(Emp emp) { this.emp = emp; }
    public Emp getEmp2() { return emp2; }
    public void setEmp2(Emp emp2) { this.emp2 = emp2; }
    public Emp getEmp3() { return emp3; }
    public void setEmp3(Emp emp3) { this.emp3 = emp3; }
    public Emp getEmp4() { return emp4; }
    public void setEmp4(Emp emp4) { this.emp4 = emp4; }
    public Supplier getSupplier() { return supplier; }
    public void setSupplier(Supplier supplier) { this.supplier = supplier; }
    public List<Ordersdetail> getOrdersdetailList() { return ordersdetailList; }
    public void setOrdersdetailList(List<Ordersdetail> ordersdetailList) { this.ordersdetailList = ordersdetailList; }
}