package com.erp.realm;

import com.erp.mapper.EmpMapper;
import com.erp.mapper.MenuMapper;
import com.erp.service.EmpService;
import com.erp.service.MenuService;
import com.yang.erp.pojo.Emp;
import com.yang.erp.pojo.Menu;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ErpRealm extends AuthorizingRealm
{
    @Autowired
    private EmpService empService;

    @Autowired
    private MenuMapper menuMapper;

    //授权方法
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal)
    {
        //获取登陆用户信息
        Emp emp = (Emp) principal.getPrimaryPrincipal();
        //根据用户信息获取下所有的菜单
        List<Menu> menus = menuMapper.queryMenuTreeBtEmpId(emp.getEid());

        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        for (Menu menu:menus)
        {
            simpleAuthorizationInfo.addStringPermission(menu.getMenuname());
        }
        return simpleAuthorizationInfo;
      /*return null;*/
    }

    //认证方法
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException
    {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
        String password = new String(usernamePasswordToken.getPassword());
        Emp emp = empService.findEmpByUsernameAndPwd(((UsernamePasswordToken) token).getUsername(),password );
        if(emp != null){
            //认证成功
            return new SimpleAuthenticationInfo(emp,password,getName());
        }
        return null;
    }


}
