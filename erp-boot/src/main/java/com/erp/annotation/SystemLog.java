package com.erp.annotation;

import java.lang.annotation.*;

/**
 * 系统操作日志注解
 * 标注在 Controller 方法上，自动记录操作日志
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
/**
 * 类描述
 *
 * @author baoyang
 */
public @interface SystemLog {

    /**
 * 日志类型
 */
    String type() default "操作日志";

    /**
 * 操作描述
 */
    String title() default "";

    /**
 * 是否记录请求参数
 */
    boolean recordParams() default true;
}