package com.yang.erp.pojo;

public class WayBill
{
    private Long sn;
    private Long userId;
    private String toaddress;
    private String addressperson;
    private String tele;
    private String info;
    private String state;
    private String createtime;
    private String isuse;
    public Long getSn()
    {
        return sn;
    }

    public void setSn(Long sn)
    {
        this.sn = sn;
    }

    public Long getUserId()
    {
        return userId;
    }

    public void setUserId(Long userId)
    {
        this.userId = userId;
    }

    public String getToaddress()
    {
        return toaddress;
    }

    public void setToaddress(String toaddress)
    {
        this.toaddress = toaddress;
    }

    public String getAddressperson()
    {
        return addressperson;
    }

    public void setAddressperson(String addressperson)
    {
        this.addressperson = addressperson;
    }

    public String getTele()
    {
        return tele;
    }

    public void setTele(String tele)
    {
        this.tele = tele;
    }

    public String getInfo()
    {
        return info;
    }

    public void setInfo(String info)
    {
        this.info = info;
    }

    public String getState()
    {
        return state;
    }

    public void setState(String state)
    {
        this.state = state;
    }

    public String getCreatetime()
    {
        return createtime;
    }

    public void setCreatetime(String createtime)
    {
        this.createtime = createtime;
    }

    public String getIsuse()
    {
        return isuse;
    }

    public void setIsuse(String isuse)
    {
        this.isuse = isuse;
    }

    @Override
    public String toString()
    {
        return "WayBill{" +
                "sn=" + sn +
                ", userId=" + userId +
                ", toaddress='" + toaddress + '\'' +
                ", addressperson='" + addressperson + '\'' +
                ", tele='" + tele + '\'' +
                ", info='" + info + '\'' +
                ", state='" + state + '\'' +
                ", createtime='" + createtime + '\'' +
                ", isuse='" + isuse + '\'' +
                '}';
    }
}
