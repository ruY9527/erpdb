package com.erp.service.impl;

import com.erp.mapper.EmpMapper;
import com.erp.mapper.RoleEmpMapper;
import com.erp.mapper.RoleMapper;
import com.erp.service.RoleEmpSetService;
import com.yang.erp.pojo.Emp;
import com.yang.erp.pojo.Role;
import com.yang.erp.pojo.RoleEmp;
import com.yang.erp.pojo.Tree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleEmpSetServiceImpl implements RoleEmpSetService
{
    @Autowired
    private EmpMapper empMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private RoleEmpMapper roleEmpMapper;

    //获取
    @Override
    public List<Tree> findAll(Long eid)
    {
        List<Tree> treeList = new ArrayList<Tree>();
     /*   Emp emp =  empMapper.selectEmpAndRole(eid);
        List<Role> empRoles = null;
        if(emp != null){
            empRoles  = emp.getRoles();
        } else {
            empRoles = new ArrayList<Role>();
        }*/
        List<Role> roles = roleMapper.selectRoleByEmpRole(eid);

        List<Role> selectRoles = roleMapper.selectAll();
        Tree t = null;
        //boolean flag = false;
        for (Role role:selectRoles)
        {
            t = new Tree();
            t.setId(String.valueOf(role.getRid()));
            t.setText(role.getName());
            if(roles !=null && roles.size()>0){
                for (Role r:roles)
                {
                    if(r.getRid().equals(role.getRid())){
                        t.setChecked(true);
                        break;
                    }
                }
            }
            treeList.add(t);
        }
        return treeList ;
    }

    //添加
    @Override
    public void updateRoleEmp(Long eid, String checkedStr)
    {
        //查询看看是不是之前已经存在
        Integer numbers =  roleEmpMapper.selectHaveByEid(eid);
        //存在
        if(numbers>0){
            //添加删除中间表的eid部分
            roleEmpMapper.deleteAllByEid(eid);
        }

        String[] split = checkedStr.split(",");
        RoleEmp roleEmp = null;
        for (String str:split)
        {
            roleEmp = new RoleEmp();
            roleEmp.setEmpId(eid);
            roleEmp.setRoleId(Long.valueOf(str));
            roleEmpMapper.add(roleEmp);
        }
    }
}

/* if(roles!=null && roles.size()>0){
                for (Role r:roles)
                {
                    if(r.getRid().equals(r.getRid())){
                        t.setChecked(true);
                        continue;
                    }
                }
            }*/