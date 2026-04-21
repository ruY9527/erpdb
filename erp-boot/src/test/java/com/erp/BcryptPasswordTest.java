package com.erp;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * BCrypt 密码测试
 *
 * @author baoyang
 */
public class BcryptPasswordTest {

    @Test
    public void testGeneratePassword() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = "123456";
        
        // 生成真实的 BCrypt 密码
        String encodedPassword = encoder.encode(rawPassword);
        
        System.out.println("========================================");
        System.out.println("原始密码: " + rawPassword);
        System.out.println("BCrypt加密后: " + encodedPassword);
        System.out.println("密码长度: " + encodedPassword.length());
        System.out.println("验证结果: " + encoder.matches(rawPassword, encodedPassword));
        System.out.println("========================================");
        
        // 验证可用于 SQL 的密码
        System.out.println("\n推荐用于SQL的密码:");
        System.out.println(encodedPassword);
    }
    
    @Test
    public void testVerifySqlPassword() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = "123456";
        
        // 测试多个可能的 BCrypt 密码
        String[] testPasswords = {
            "$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJm4klO2h6",
            "$2a$10$EqKcp1WFKpQ5Mv9Zy7lV.e7X9HZvP0GkC8n7h0n7l8vP0GkC8n7h0n7l",
            "$2a$10$dXJ3SW6G7P50lGmMkkmwe.0c8M9M5uYVbXe5.h5.h5.h5.h5.h5.h5"
        };
        
        System.out.println("验证测试密码:");
        for (String testPwd : testPasswords) {
            boolean matches = encoder.matches(rawPassword, testPwd);
            System.out.println(testPwd + " -> " + matches);
        }
        
        // 生成一个真实的密码
        String realEncoded = encoder.encode(rawPassword);
        System.out.println("\n真实有效的BCrypt密码: " + realEncoded);
        System.out.println("请将此密码更新到数据库中");
    }
}