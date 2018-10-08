package com.yang.erp.pojo;

public class WayBillDetail
{
    private Long id;  //详情表id
    private Long su;  //运单号
    private String execute;  //执行日期
    private String exetime;  //执行时间
    private String info; //信息

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getSu()
    {
        return su;
    }

    public void setSu(Long su)
    {
        this.su = su;
    }

    public String getExecute()
    {
        return execute;
    }

    public void setExecute(String execute)
    {
        this.execute = execute;
    }

    public String getExetime()
    {
        return exetime;
    }

    public void setExetime(String exetime)
    {
        this.exetime = exetime;
    }

    public String getInfo()
    {
        return info;
    }

    public void setInfo(String info)
    {
        this.info = info;
    }

    @Override
    public String toString()
    {
        return "WayBillDetail{" +
                "id=" + id +
                ", su=" + su +
                ", execute='" + execute + '\'' +
                ", exetime='" + exetime + '\'' +
                ", info='" + info + '\'' +
                '}';
    }
}
