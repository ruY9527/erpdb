package com.erp.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 仓库
 *
 * @author baoyang
 */
@TableName("store")
public class Store {

    public static final String USE = "1";
    public static final String NO_USE = "0";

    @TableId("sid")
    private Long sid;

    private String name;

    private String address;

    @TableField("empId")
    private Long empId;

    @TableField(exist = false)
    private Emp emp;

    private String state;

    public Long getSid() { return sid; }
    public void setSid(Long sid) { this.sid = sid; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public Long getEmpId() { return empId; }
    public void setEmpId(Long empId) { this.empId = empId; }
    public Emp getEmp() { return emp; }
    public void setEmp(Emp emp) { this.emp = emp; }
    public String getState() { return state; }
    public void setState(String state) { this.state = state; }
}