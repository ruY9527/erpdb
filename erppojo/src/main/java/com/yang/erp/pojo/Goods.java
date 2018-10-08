package com.yang.erp.pojo;

public class Goods {
    private Long gsid;  //商品id
    private String name;   //商品名字
    private String  origin;  //商品的产地
    private String producer;  //商品的厂商
    private String unit;     //计量的单位
    private Double inprice;   //进价
    private Double outprice;  //出价
    private GoodsType goodsType;  //商品对应的类型
    private String state;

    public Long getGsid() {
        return gsid;
    }

    public void setGsid(Long gsid) {
        this.gsid = gsid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Double getInprice() {
        return inprice;
    }

    public void setInprice(Double inprice) {
        this.inprice = inprice;
    }

    public Double getOutprice() {
        return outprice;
    }

    public void setOutprice(Double outprice) {
        this.outprice = outprice;
    }

    public GoodsType getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(GoodsType goodsType) {
        this.goodsType = goodsType;
    }

    public String getState()
    {
        return state;
    }

    public void setState(String state)
    {
        this.state = state;
    }

    @Override
    public String toString()
    {
        return "Goods{" +
                "gsid=" + gsid +
                ", name='" + name + '\'' +
                ", origin='" + origin + '\'' +
                ", producer='" + producer + '\'' +
                ", unit='" + unit + '\'' +
                ", inprice=" + inprice +
                ", outprice=" + outprice +
                ", goodsType=" + goodsType +
                ", state='" + state + '\'' +
                '}';
    }
}
