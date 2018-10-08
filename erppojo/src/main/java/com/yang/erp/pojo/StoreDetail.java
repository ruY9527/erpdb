package com.yang.erp.pojo;

public class StoreDetail {
    private Long sdid;   //商品仓库的id
    private Long storeId;  //仓库id
    private Store store;   //对应仓库的信息
    private Long goodsId;   //商品id对应
    private Goods goods;    //对应商品信息
    private Long num;    //库存数量

    public Long getSdid() {
        return sdid;
    }

    public void setSdid(Long sdid) {
        this.sdid = sdid;
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

    @Override
    public String toString() {
        return "StoreDetail{" +
                "sdid=" + sdid +
                ", storeId=" + storeId +
                ", store=" + store +
                ", goodsId=" + goodsId +
                ", goods=" + goods +
                ", num=" + num +
                '}';
    }
}
