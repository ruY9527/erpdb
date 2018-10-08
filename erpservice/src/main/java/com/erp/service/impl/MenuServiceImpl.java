package com.erp.service.impl;

import com.erp.mapper.MenuMapper;
import com.erp.service.MenuService;
import com.yang.erp.pojo.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    //查询出tree形状菜单
    @Override
    public Menu queryTreeList(String pid) {
        Menu menuList = menuMapper.queryMenuTreeList(pid);
       /* for (Menu m: menuList) {
            getTreeNode(m);
        }*/
        return menuList;
    }

    //遍历获取子代的tree
   /* public void getTreeNode(Menu menu){
        Menu menuList = menu.getMenuList();
        if(menuList != null && menuList.size()>0){
            for(Menu m:menuList){
                getTreeNode(m);
            }
        }
    }*/

   //根据eid来显示菜单
    @Override
    public Menu queryMenuTreeBtEmpId(Long eid)
    {
        //根据eid获取的菜单
        List<Menu> menuList =  menuMapper.queryMenuTreeBtEmpId(eid);
        //全部菜单
        Menu menu = menuMapper.queryMenuTreeList("0");
        //复制根目录
        Menu cloneMenu = cloneMenu(menu);
        Menu menu1 = null;
        Menu menu2 = null;
        for (Menu m1:menu.getMenus())
        {
            //复制一级菜单
            menu1 =  cloneMenu(m1);
           /* if(menuList !=null && menuList.size()>0){
                for (Menu m11:menuList)
                {
                    if(m11.getMenuid().equals(m1.getMenuid())){
                        menu1 = cloneMenu(m11);
                        break;
                    }
                }
            }*/

            //二级菜单
            for(Menu m2:m1.getMenus()){
                if(menuList !=null && menuList.size()>0){
                    for (Menu m22:menuList)
                    {
                        if(m22.getMenuid().equals(m2.getMenuid())){
                            menu2 = cloneMenu(m22);
                            menu1.getMenus().add(menu2);
                            break;
                        }
                    }
                }
            }
            //如果一级菜单下有二级菜单
            if(menu1 !=null){
                if(menu1.getMenus().size()>0){
                    cloneMenu.getMenus().add(menu1);
                }
            }

        }
        return cloneMenu;
    }


    //菜单复制
    private Menu cloneMenu(Menu srcMenu){
        Menu _menu = new Menu();
        _menu.setMenuid(srcMenu.getMenuid());
        _menu.setMenuname(srcMenu.getMenuname());
        _menu.setIcon(srcMenu.getIcon());
        _menu.setUrl(srcMenu.getUrl());
        _menu.setMenus(new ArrayList<>());
        return _menu;
    }

}
