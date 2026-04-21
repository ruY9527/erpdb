package com.erp.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * BCrypt 密码生成工具
 * 用于生成测试密码
 *
 *
 * @author baoyang
 */
public class PasswordGenerator {

    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        
        // 生成 123456 的 BCrypt 密码
        String password = "123456";
        String encodedPassword = encoder.encode(password);
        
        System.out.println("========================================");
        System.out.println("原始密码: " + password);
        System.out.println("BCrypt加密后: " + encodedPassword);
        System.out.println("密码长度: " + encodedPassword.length());
        System.out.println("========================================");
        
        // 验证密码是否匹配
        boolean matches = encoder.matches(password, encodedPassword);
        System.out.println("验证结果: " + matches);
        
        // 生成多个示例（BCrypt每次加密结果不同，但都能验证成功）
        System.out.println("\n生成多个BCrypt密码示例：");
        for (int i = 0; i < 3; i++) {
            String encoded = encoder.encode(password);
            System.out.println(encoded);
        }
    }
}