package com.yang.erp.pojo;

import java.util.List;

public class ReturnOrders
{
    public static final String ON_CHECK = "0";  //未审核
    public static final String HAVE_CHECK = "1";  //已审核
    public static final String SALRS_RETURN = "2";  //已退货

    private Long roid;  //退货订单id
    private String createtime;  //创建时间
    private String checktime;  //检查日期
    private String endtime;   //结束日期
    private String type;  //订单类型  1采购 2销售
    private Long createer;  //  下单员id
    private Emp createEmp;   //下单员po
    private Long checker;  //检查员id
    private Emp checkEmp;  //检查员对应的po
    private Long ender;  //结束员id
    private Emp endEmp;   //结束员po
    private Long supplierId;  //客户或者供应商id
    private Supplier supplier;   //对应的po类
    private Double totalmoney;  //合计金额
    private String state;   //订单的状态
    private String waybillsn;  //运单号
    private Long ordersOid;  //原订单号
    private Orders orders;

    private List<ReturnOrdersDetail> returnOrdersDetailList;
    public Long getRoid()
    {
        return roid;
    }

    public void setRoid(Long roid)
    {
        this.roid = roid;
    }

    public String getCreatetime()
    {
        return createtime;
    }

    public void setCreatetime(String createtime)
    {
        this.createtime = createtime;
    }

    public String getChecktime()
    {
        return checktime;
    }

    public void setChecktime(String checktime)
    {
        this.checktime = checktime;
    }

    public String getEndtime()
    {
        return endtime;
    }

    public void setEndtime(String endtime)
    {
        this.endtime = endtime;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public Long getCreateer()
    {
        return createer;
    }

    public void setCreateer(Long createer)
    {
        this.createer = createer;
    }

    public Emp getCreateEmp()
    {
        return createEmp;
    }

    public void setCreateEmp(Emp createEmp)
    {
        this.createEmp = createEmp;
    }

    public Long getChecker()
    {
        return checker;
    }

    public void setChecker(Long checker)
    {
        this.checker = checker;
    }

    public Emp getCheckEmp()
    {
        return checkEmp;
    }

    public void setCheckEmp(Emp checkEmp)
    {
        this.checkEmp = checkEmp;
    }

    public Long getEnder()
    {
        return ender;
    }

    public void setEnder(Long ender)
    {
        this.ender = ender;
    }

    public Emp getEndEmp()
    {
        return endEmp;
    }

    public void setEndEmp(Emp endEmp)
    {
        this.endEmp = endEmp;
    }

    public Long getSupplierId()
    {
        return supplierId;
    }

    public void setSupplierId(Long supplierId)
    {
        this.supplierId = supplierId;
    }

    public Supplier getSupplier()
    {
        return supplier;
    }

    public void setSupplier(Supplier supplier)
    {
        this.supplier = supplier;
    }

    public Double getTotalmoney()
    {
        return totalmoney;
    }

    public void setTotalmoney(Double totalmoney)
    {
        this.totalmoney = totalmoney;
    }

    public String getState()
    {
        return state;
    }

    public void setState(String state)
    {
        this.state = state;
    }

    public String getWaybillsn()
    {
        return waybillsn;
    }

    public void setWaybillsn(String waybillsn)
    {
        this.waybillsn = waybillsn;
    }

    public Long getOrdersOid()
    {
        return ordersOid;
    }

    public void setOrdersOid(Long ordersOid)
    {
        this.ordersOid = ordersOid;
    }

    public Orders getOrders()
    {
        return orders;
    }

    public void setOrders(Orders orders)
    {
        this.orders = orders;
    }

    public List<ReturnOrdersDetail> getReturnOrdersDetailList()
    {
        return returnOrdersDetailList;
    }

    public void setReturnOrdersDetailList(List<ReturnOrdersDetail> returnOrdersDetailList)
    {
        this.returnOrdersDetailList = returnOrdersDetailList;
    }

    @Override
    public String toString()
    {
        return "ReturnOrders{" +
                "roid=" + roid +
                ", createtime='" + createtime + '\'' +
                ", checktime='" + checktime + '\'' +
                ", endtime='" + endtime + '\'' +
                ", type='" + type + '\'' +
                ", createer=" + createer +
                ", createEmp=" + createEmp +
                ", checker=" + checker +
                ", checkEmp=" + checkEmp +
                ", ender=" + ender +
                ", endEmp=" + endEmp +
                ", supplierId=" + supplierId +
                ", supplier=" + supplier +
                ", totalmoney=" + totalmoney +
                ", state='" + state + '\'' +
                ", waybillsn='" + waybillsn + '\'' +
                ", ordersOid=" + ordersOid +
                ", orders=" + orders +
                ", returnOrdersDetailList=" + returnOrdersDetailList +
                '}';
    }
}
