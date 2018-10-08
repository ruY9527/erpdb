package com.erp.service.impl;

import com.erp.mapper.MenuMapper;
import com.erp.mapper.RoleMapper;
import com.erp.mapper.RoleMenuSetMapper;
import com.erp.service.RoleMenuSetService;
import com.yang.erp.pojo.Menu;
import com.yang.erp.pojo.Role;
import com.yang.erp.pojo.RoleMenu;
import com.yang.erp.pojo.Tree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleMenuSetServiceImpl implements RoleMenuSetService
{
    @Autowired
    private RoleMenuSetMapper roleMenuSetMapper;

    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<Tree> findAll(Long rid)
    {
        List<Tree> treeList = new ArrayList<Tree>();
        //获取角色信息中的菜单信息
        Role roleMenuByRid = roleMapper.getRoleMenuByRid(rid);
        List<Menu> menuList = null;
        if(roleMenuByRid !=null){
            menuList =  roleMenuByRid.getMenus();
        }
        //跟菜单
        Menu menu = menuMapper.queryMenuTreeList("0");
        Tree t1 =null;
        Tree t2 =null;
        for (Menu m1:menu.getMenus())
        {
            t1 = new Tree();  //一级菜单
            t1.setId(m1.getMenuid());
            t1.setText(m1.getMenuname());
            for(Menu m2:m1.getMenus()){
                t2 = new Tree();  //二级菜单
                t2.setId(m2.getMenuid());
                t2.setText(m2.getMenuname());
              /*  if(menuList.contains(m2)){
                    t2.setChecked(true);
                }*/
              if(menuList !=null && menuList.size()>0){
                  //判断路面是否已经存在
                  for (Menu m:menuList)
                  {
                      if(m.getMenuid().equals(m2.getMenuid())){
                          t2.setChecked(true);
                          break;
                      }
                  }
              }
                t1.getChildren().add(t2);
            }
            treeList.add(t1);
        }
        return treeList;
    }

    //插入到中间表
    @Override
    public void updateRoleMenus(Long rid, String checkedStr)
    {
       //添加之前，进行该角色是否已经的判断
        Integer numbers =  roleMenuSetMapper.selectCountByRid(rid);
        //存在的判断
        if(numbers >0){
            roleMenuSetMapper.deleteAllByRid(rid);
        }
        //获取菜单id
        String[] split = checkedStr.split(",");
        RoleMenu roleMenu = null;
        for (String s:split)
        {
            roleMenu = new RoleMenu();
            roleMenu.setRoleId(rid);
            roleMenu.setMenuId(Long.valueOf(s));
            roleMenuSetMapper.add(roleMenu);
        }


    }


}
