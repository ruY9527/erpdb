package com.erp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * ERP 管理系统启动类
 *
 * @author baoyang
 */
@SpringBootApplication
@MapperScan("com.erp.mapper")
public class ErpApplication {

    public static void main(String[] args) {
        SpringApplication.run(ErpApplication.class, args);
        System.out.println("========================================");
        System.out.println("ERP管理系统启动成功！");
        System.out.println("API文档地址: http://localhost:8080/doc.html");
        System.out.println("========================================");
    }
}