package com.erp.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 部门实体
 *
 * @author baoyang
 */
@TableName("dept")
public class Dept {

    @TableId("did")
    private Long did;

    private String name;
    private String tele;
    private String state;

    public Long getDid() { return did; }
    public void setDid(Long did) { this.did = did; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getTele() { return tele; }
    public void setTele(String tele) { this.tele = tele; }
    public String getState() { return state; }
    public void setState(String state) { this.state = state; }
}