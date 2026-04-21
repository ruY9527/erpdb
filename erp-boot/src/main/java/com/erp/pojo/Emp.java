package com.erp.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.List;

/**
 * 员工实体
 *
 * @author baoyang
 */
@TableName("emp")
public class Emp implements Serializable {

    public static final String USE = "1";
    public static final String NO_USE = "0";

    @TableId("eid")
    private Long eid;

    private String username;

    @JSONField(serialize = false)
    private String pwd;

    private String name;
    private Integer gender;
    private String email;
    private String tele;
    private String address;
    private String birthday;

    @TableField("deptId")
    private Long deptId;

    private String state;

    @TableField(exist = false)
    private Dept dept;

    @TableField(exist = false)
    private String birthdays;

    @JSONField(serialize = false)
    @TableField(exist = false)
    private List<Role> roles;

    public Long getEid() { return eid; }
    public void setEid(Long eid) { this.eid = eid; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPwd() { return pwd; }
    public void setPwd(String pwd) { this.pwd = pwd; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Integer getGender() { return gender; }
    public void setGender(Integer gender) { this.gender = gender; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getTele() { return tele; }
    public void setTele(String tele) { this.tele = tele; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public String getBirthday() { return birthday; }
    public void setBirthday(String birthday) { this.birthday = birthday; }
    public Long getDeptId() { return deptId; }
    public void setDeptId(Long deptId) { this.deptId = deptId; }
    public String getState() { return state; }
    public void setState(String state) { this.state = state; }
    public Dept getDept() { return dept; }
    public void setDept(Dept dept) { this.dept = dept; }
    public String getBirthdays() { return birthdays; }
    public void setBirthdays(String birthdays) { this.birthdays = birthdays; }
    public List<Role> getRoles() { return roles; }
    public void setRoles(List<Role> roles) { this.roles = roles; }
}