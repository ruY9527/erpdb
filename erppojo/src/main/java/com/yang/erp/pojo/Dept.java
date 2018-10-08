package com.yang.erp.pojo;

public class Dept {
    private Long did;  //部门id
    private String name;  //部门名称
    private String tele;  //部门电话


    public Long getDid() {
        return did;
    }

    public void setDid(Long did) {
        this.did = did;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTele() {
        return tele;
    }

    public void setTele(String tele) {
        this.tele = tele;
    }

    @Override
    public String toString() {
        return "Dept{" +
                "did=" + did +
                ", name='" + name + '\'' +
                ", tele='" + tele + '\'' +
                '}';
    }
}
