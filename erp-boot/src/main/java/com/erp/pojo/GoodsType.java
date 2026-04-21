package com.erp.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.List;

/**
 * 商品类型实体
 *
 * @author baoyang
 */
@TableName("goodstype")
public class GoodsType {

    @TableId("gid")
    private Long gid;

    private String name;
    private String state;

    @TableField(exist = false)
    private List<GoodsType> children;

    public Long getGid() { return gid; }
    public void setGid(Long gid) { this.gid = gid; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getState() { return state; }
    public void setState(String state) { this.state = state; }
    public List<GoodsType> getChildren() { return children; }
    public void setChildren(List<GoodsType> children) { this.children = children; }
}