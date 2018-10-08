package com.yang.erp.pojo;

public class GoodsType {
    private Long gid;  //商品分类的id
    private String name;  //商品的类型名字
    private String state;   //1正常 0虚拟删除

    public Long getGid() {
        return gid;
    }

    public void setGid(Long gid) {
        this.gid = gid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        return "GoodsType{" +
                "gid=" + gid +
                ", name='" + name + '\'' +
                ", state='" + state + '\'' +
                '}';
    }
}
