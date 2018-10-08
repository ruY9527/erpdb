package com.yang.erp.pojo;

/**
 * 库存警报操作
 */
public class StoreAlert
{
    private Long gsid;  //商品编号
    private String name; //商品名字
    private Long storenum;//库存数量
    private Long outnum;  //代发数量

    public Long getGsid()
    {
        return gsid;
    }

    public void setGsid(Long gsid)
    {
        this.gsid = gsid;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Long getStorenum()
    {
        return storenum;
    }

    public void setStorenum(Long storenum)
    {
        this.storenum = storenum;
    }

    public Long getOutnum()
    {
        return outnum;
    }

    public void setOutnum(Long outnum)
    {
        this.outnum = outnum;
    }

    @Override
    public String toString()
    {
        return "StoreAlert{" +
                "gsid=" + gsid +
                ", name='" + name + '\'' +
                ", storenum=" + storenum +
                ", outnum=" + outnum +
                '}';
    }
}
