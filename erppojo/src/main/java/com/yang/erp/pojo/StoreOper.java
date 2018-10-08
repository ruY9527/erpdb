package com.yang.erp.pojo;

public class StoreOper {
    private Long soid;  //商品仓库仓库操作记录
    private Long empId;  //员工id
    private Emp emp;   //员工的id
    private String opertime;  //操作时间
    private Long storeId;  //仓库id
    private Store store;   //对应仓库的id
    private Long goodsId;  //商品id
    private Goods goods;  //对应商品的id
    private Long num;   //数量
    private String type;  //1.出库  2.出库
    private String opertimes; //便于查询操作

    public static final String TYPE_IN = "1";  //入库
    public static final String TYPE_OUT = "2"; //出库

    public Long getSoid() {
        return soid;
    }

    public void setSoid(Long soid) {
        this.soid = soid;
    }

    public Emp getEmp() {
        return emp;
    }

    public void setEmp(Emp emp) {
        this.emp = emp;
    }

    public String getOpertime() {
        return opertime;
    }

    public void setOpertime(String opertime) {
        this.opertime = opertime;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public Long getNum() {
        return num;
    }

    public void setNum(Long num) {
        this.num = num;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getEmpId() {
        return empId;
    }

    public void setEmpId(Long empId) {
        this.empId = empId;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public String getOpertimes()
    {
        return opertimes;
    }

    public void setOpertimes(String opertimes)
    {
        this.opertimes = opertimes;
    }

    @Override
    public String toString()
    {
        return "StoreOper{" +
                "soid=" + soid +
                ", empId=" + empId +
                ", emp=" + emp +
                ", opertime='" + opertime + '\'' +
                ", storeId=" + storeId +
                ", store=" + store +
                ", goodsId=" + goodsId +
                ", goods=" + goods +
                ", num=" + num +
                ", type='" + type + '\'' +
                ", opertimes='" + opertimes + '\'' +
                '}';
    }
}
