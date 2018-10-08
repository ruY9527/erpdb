package com.erp.realm;

import org.apache.shiro.web.filter.authz.AuthorizationFilter;

import javax.security.auth.Subject;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class ErpOrdersAuthorizationFilter extends AuthorizationFilter
{

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object object) throws Exception
    {
        //获取主题
        org.apache.shiro.subject.Subject subject = getSubject(request, response);
        String [] perms =  (String [])object;
        //如果长度是0，则放行
        if(perms == null || perms.length==0){
            return  true;
        }
        //权限检查
        for (String str:perms)
        {
            if(subject.isPermitted(str)){
                return true;
            }
        }
        return false;
    }
}
