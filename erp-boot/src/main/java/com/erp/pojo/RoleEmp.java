package com.erp.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 角色员工关联
 *
 * @author baoyang
 */
@TableName("roleemp")
public class RoleEmp {

    @TableId
    private Long rid;
    private Long empId;

    public Long getRid() { return rid; }
    public void setRid(Long rid) { this.rid = rid; }
    public Long getEmpId() { return empId; }
    public void setEmpId(Long empId) { this.empId = empId; }
}