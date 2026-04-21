package com.erp.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * BCrypt 密码验证工具
 *
 * @author baoyang
 */
public class BcryptVerifier {

    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        
        // 验证密码是否匹配
        String rawPassword = "123456";
        String encodedPassword = "$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJm4klO2h6";
        
        System.out.println("========================================");
        System.out.println("原始密码: " + rawPassword);
        System.out.println("加密密码: " + encodedPassword);
        System.out.println("密码长度: " + encodedPassword.length());
        
        boolean matches = encoder.matches(rawPassword, encodedPassword);
        System.out.println("验证结果: " + matches);
        System.out.println("========================================");
        
        if (!matches) {
            // 如果验证失败，生成新的正确密码
            System.out.println("\n验证失败！生成新的正确BCrypt密码：");
            String newEncoded = encoder.encode(rawPassword);
            System.out.println("新密码: " + newEncoded);
            System.out.println("新密码验证: " + encoder.matches(rawPassword, newEncoded));
        }
    }
}