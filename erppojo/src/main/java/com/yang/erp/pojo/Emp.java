package com.yang.erp.pojo;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Emp implements Serializable{
    //定义state使用的状态
    public static final String USE = "1";
    public static final String NO_USE = "0";

    private Long eid;
    private String username;
    @JSONField(serialize = false)
    private String pwd;
    private String name;
    private Integer gender;   // 1.男  0女
    private String email;
    private String tele;
    private String address;
    private String birthday;
    private String state;   //0禁用  1正常使用
    private Dept dept;
    //添加一个birthdays用于查询年到年之间
    private String birthdays;

    @JSONField(serialize = false)
    private List<Role> roles;  //拥有的角色

    public Long getEid()
    {
        return eid;
    }

    public void setEid(Long eid)
    {
        this.eid = eid;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPwd()
    {
        return pwd;
    }

    public void setPwd(String pwd)
    {
        this.pwd = pwd;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Integer getGender()
    {
        return gender;
    }

    public void setGender(Integer gender)
    {
        this.gender = gender;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getTele()
    {
        return tele;
    }

    public void setTele(String tele)
    {
        this.tele = tele;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public String getBirthday()
    {
        return birthday;
    }

    public void setBirthday(String birthday)
    {
        this.birthday = birthday;
    }

    public Dept getDept()
    {
        return dept;
    }

    public void setDept(Dept dept)
    {
        this.dept = dept;
    }

    public String getBirthdays()
    {
        return birthdays;
    }

    public void setBirthdays(String birthdays)
    {
        this.birthdays = birthdays;
    }

    public List<Role> getRoles()
    {
        return roles;
    }

    public void setRoles(List<Role> roles)
    {
        this.roles = roles;
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
        return "Emp{" +
                "eid=" + eid +
                ", username='" + username + '\'' +
                ", pwd='" + pwd + '\'' +
                ", name='" + name + '\'' +
                ", gender=" + gender +
                ", email='" + email + '\'' +
                ", tele='" + tele + '\'' +
                ", address='" + address + '\'' +
                ", birthday='" + birthday + '\'' +
                ", state='" + state + '\'' +
                ", dept=" + dept +
                ", birthdays='" + birthdays + '\'' +
                ", roles=" + roles +
                '}';
    }
}
