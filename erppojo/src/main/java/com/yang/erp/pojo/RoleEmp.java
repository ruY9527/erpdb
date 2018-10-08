package com.yang.erp.pojo;

public class RoleEmp
{
    private Long empId;  //用户id
    private Long roleId;  //权限id

    public Long getEmpId()
    {
        return empId;
    }

    public void setEmpId(Long empId)
    {
        this.empId = empId;
    }

    public Long getRoleId()
    {
        return roleId;
    }

    public void setRoleId(Long roleId)
    {
        this.roleId = roleId;
    }

    @Override
    public String toString()
    {
        return "RoleEmp{" +
                "empId=" + empId +
                ", roleId=" + roleId +
                '}';
    }
}
