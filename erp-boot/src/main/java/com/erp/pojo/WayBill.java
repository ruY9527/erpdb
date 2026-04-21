package com.erp.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 运单
 *
 * @author baoyang
 */
@TableName("waybill")
public class WayBill {

    @TableId("sn")
    private Long sn;
    private Long userId;
    private String toaddress;
    private String createtime;
    private String type;
    private Long ordersId;
    private String state;

    public Long getSn() { return sn; }
    public void setSn(Long sn) { this.sn = sn; }
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    public String getToaddress() { return toaddress; }
    public void setToaddress(String toaddress) { this.toaddress = toaddress; }
    public String getCreatetime() { return createtime; }
    public void setCreatetime(String createtime) { this.createtime = createtime; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public Long getOrdersId() { return ordersId; }
    public void setOrdersId(Long ordersId) { this.ordersId = ordersId; }
    public String getState() { return state; }
    public void setState(String state) { this.state = state; }
}