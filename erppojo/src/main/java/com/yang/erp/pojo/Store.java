package com.yang.erp.pojo;

public class Store {

    public static final String USE="1";
    public static final String NO_USE = "0";

    private Long sid;   //仓库的id
    private String name;  //仓库名称
    private String state;  //使用停用的状态
    private Emp emp;    //对应管理员(emp)

    public Long getSid() {
        return sid;
    }

    public void setSid(Long sid) {
        this.sid = sid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Emp getEmp() {
        return emp;
    }

    public void setEmp(Emp emp) {
        this.emp = emp;
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
        return "Store{" +
                "sid=" + sid +
                ", name='" + name + '\'' +
                ", state='" + state + '\'' +
                ", emp=" + emp +
                '}';
    }
}
