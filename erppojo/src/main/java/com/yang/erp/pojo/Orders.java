package com.yang.erp.pojo;

import java.util.List;

public class Orders {
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
    private Long supplierId;
    private Double totalmoney;
    private String state;
    private String waybillsn;

    private Emp emp;  //对应的emp员工
    private Emp emp2;  //对应checker检查员工
    private Emp emp3; //对应确认员工
    private Emp emp4;  //对应结束员工
    private Supplier supplier;  //对应的客户

    private List<Ordersdetail> ordersdetailList;  //对应一个或者多个订单明细表单

    public static final String STATE_CREATE = "0";  //未审核
    public static final String STATE_CHECK = "1";   //已审核
    public static final String STATE_START = "2";   //已确认
    public static final String STATE_EDN =   "3";   //已入库

    public static final String TYPE_IN  = "1";   //采购订单
    public static final String TYPE_OUT = "2";   //销售订单
    public Long getOid() {
        return oid;
    }

    public void setOid(Long oid) {
        this.oid = oid;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getChecktime() {
        return checktime;
    }

    public void setChecktime(String checktime) {
        this.checktime = checktime;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getCreateer() {
        return createer;
    }

    public void setCreateer(Long createer) {
        this.createer = createer;
    }

    public Long getChecker() {
        return checker;
    }

    public void setChecker(Long checker) {
        this.checker = checker;
    }

    public Long getStarter() {
        return starter;
    }

    public void setStarter(Long starter) {
        this.starter = starter;
    }

    public Long getEnder() {
        return ender;
    }

    public void setEnder(Long ender) {
        this.ender = ender;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public Double getTotalmoney() {
        return totalmoney;
    }

    public void setTotalmoney(Double totalmoney) {
        this.totalmoney = totalmoney;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getWaybillsn() {
        return waybillsn;
    }

    public void setWaybillsn(String waybillsn) {
        this.waybillsn = waybillsn;
    }

    public Emp getEmp() {
        return emp;
    }

    public void setEmp(Emp emp) {
        this.emp = emp;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public List<Ordersdetail> getOrdersdetailList() {
        return ordersdetailList;
    }

    public void setOrdersdetailList(List<Ordersdetail> ordersdetailList) {
        this.ordersdetailList = ordersdetailList;
    }

    public Emp getEmp2() {
        return emp2;
    }

    public void setEmp2(Emp emp2) {
        this.emp2 = emp2;
    }

    public Emp getEmp3() {
        return emp3;
    }

    public void setEmp3(Emp emp3) {
        this.emp3 = emp3;
    }

    public Emp getEmp4() {
        return emp4;
    }

    public void setEmp4(Emp emp4) {
        this.emp4 = emp4;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "oid=" + oid +
                ", createtime='" + createtime + '\'' +
                ", checktime='" + checktime + '\'' +
                ", starttime='" + starttime + '\'' +
                ", endtime='" + endtime + '\'' +
                ", type='" + type + '\'' +
                ", createer=" + createer +
                ", checker=" + checker +
                ", starter=" + starter +
                ", ender=" + ender +
                ", supplierId=" + supplierId +
                ", totalmoney=" + totalmoney +
                ", state='" + state + '\'' +
                ", waybillsn='" + waybillsn + '\'' +
                ", emp=" + emp +
                ", emp2=" + emp2 +
                ", emp3=" + emp3 +
                ", emp4=" + emp4 +
                ", supplier=" + supplier +
                ", ordersdetailList=" + ordersdetailList +
                '}';
    }
}
