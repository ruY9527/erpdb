package com.erp.aspect;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * 请求日志切面
 *
 * 对所有 Controller 的请求进行日志记录，包括：
 * - 请求 URL、方法、参数
 * - 响应结果、耗时
 *
 * @author baoyang System
 * @version 1.0
 */
@Slf4j
@Aspect
@Component
public class RequestLogAspect {

    /**
 * 定义切点：拦截所有 Controller 的方法
 */
    @Pointcut("execution(* com.erp.controller.*.*(..))")
    public void controllerPointcut() {
    }

    /**
 * 环绕通知：记录请求和响应日志
 *
     * @param joinPoint 连接点
     * @return 方法执行结果
     * @throws Throwable 方法执行异常
 */
    @Around("controllerPointcut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        
        // 获取请求信息
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes == null) {
            return joinPoint.proceed();
        }
        
        HttpServletRequest request = attributes.getRequest();
        
        // 记录请求信息
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = joinPoint.getSignature().getName();
        String requestUrl = request.getRequestURL().toString();
        String httpMethod = request.getMethod();
        String remoteAddr = getRemoteAddr(request);
        
        // 获取请求参数
        Object[] args = joinPoint.getArgs();
        String requestParams = getRequestParams(args, request);
        
        // 打印请求日志
        log.info("========== 请求开始 ==========");
        log.info("请求URL: {} {}", httpMethod, requestUrl);
        log.info("请求来源: {}", remoteAddr);
        log.info("目标方法: {}.{}", className, methodName);
        log.info("请求参数: {}", requestParams);
        
        try {
            // 执行目标方法
            Object result = joinPoint.proceed();
            
            // 计算耗时
            long endTime = System.currentTimeMillis();
            long duration = endTime - startTime;
            
            // 打印响应日志
            String responseResult = JSON.toJSONString(result);
            log.info("响应结果: {}", responseResult);
            log.info("请求耗时: {}ms", duration);
            log.info("========== 请求结束 ========== [{}]", requestUrl);
            
            return result;
        } catch (Throwable e) {
            // 计算耗时
            long endTime = System.currentTimeMillis();
            long duration = endTime - startTime;
            
            // 打印异常日志
            log.error("========== 请求异常 ========== [{}]", requestUrl);
            log.error("异常信息: {}", e.getMessage());
            log.error("请求耗时: {}ms", duration);
            throw e;
        }
    }

    /**
 * 获取请求参数
 *
     * @param args 方法参数
     * @param request HTTP请求
     * @return 参数JSON字符串
 */
    private String getRequestParams(Object[] args, HttpServletRequest request) {
        Map<String, Object> params = new HashMap<>();
        
        // 获取 URL 查询参数
        Enumeration<String> paramNames = request.getParameterNames();
        while (paramNames.hasMoreElements()) {
            String name = paramNames.nextElement();
            params.put(name, request.getParameter(name));
        }
        
        // 获取方法参数（排除 HttpServletRequest/Response 等类型）
        if (args != null && args.length > 0) {
            for (int i = 0; i < args.length; i++) {
                Object arg = args[i];
                if (arg != null && !isServletRequestOrResponse(arg)) {
                    try {
                        // 使用 fastjson 序列化参数
                        params.put("body_" + i, JSON.toJSONString(arg));
                    } catch (Exception e) {
                        params.put("body_" + i, arg.toString());
                    }
                }
            }
        }
        
        if (params.isEmpty()) {
            return "{}";
        }
        
        try {
            return JSON.toJSONString(params);
        } catch (Exception e) {
            return params.toString();
        }
    }

    /**
 * 判断是否为 ServletRequest/Response 类型
 *
     * @param arg 参数对象
     * @return true-是请求响应对象
 */
    private boolean isServletRequestOrResponse(Object arg) {
        return arg instanceof HttpServletRequest 
            || arg instanceof javax.servlet.http.HttpServletResponse
            || arg.getClass().getName().contains("ServletResponse");
    }

    /**
 * 获取客户端真实IP地址
 *
     * @param request HTTP请求
     * @return IP地址
 */
    private String getRemoteAddr(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        // 对于多级代理，取第一个IP
        if (ip != null && ip.contains(",")) {
            ip = ip.split(",")[0].trim();
        }
        return ip;
    }
}