package com.erp.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 供应商/客户
 *
 * @author baoyang
 */
@TableName("supplier")
public class Supplier {

    public static final String TYPE_SUPPLIER = "1";
    public static final String TYPE_CUSTOMER = "2";

    @TableId("suid")
    private Long suid;

    private String name;
    private String address;
    private String contact;
    private String tele;
    private String email;
    private String type;
    private String state;

    public Long getSuid() { return suid; }
    public void setSuid(Long suid) { this.suid = suid; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public String getContact() { return contact; }
    public void setContact(String contact) { this.contact = contact; }
    public String getTele() { return tele; }
    public void setTele(String tele) { this.tele = tele; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public String getState() { return state; }
    public void setState(String state) { this.state = state; }
}