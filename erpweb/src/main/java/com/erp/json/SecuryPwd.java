package com.erp.json;

import org.apache.shiro.crypto.hash.Md5Hash;

public class SecuryPwd
{
    //加密次数hashIterations
    private static final int HASHITERATIONS = 2;

    public static String encrey(String pwd,String salt){
        //src原密码,pwd现盐,HASHITERATIONS加密次数
        Md5Hash md5Hash = new Md5Hash(pwd,salt,HASHITERATIONS);
        return md5Hash.toString();
    }
}
