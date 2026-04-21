package com.erp.aop;

import com.alibaba.fastjson.JSON;
import com.erp.annotation.SystemLog;
import com.erp.mapper.LogMapper;
import com.erp.pojo.Log;
import com.erp.security.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * 操作日志切面
 * 自动记录 Controller 方法的操作日志
 */
@Slf4j
@Aspect
@Component
/**
 * Log切面
 *
 * @author baoyang
 */
public class LogAspect {

    @Autowired
    private LogMapper logMapper;

    @Autowired
    private JwtUtils jwtUtils;

    @Around("@annotation(com.erp.annotation.SystemLog)")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        long startTime = System.currentTimeMillis();
        
        // 获取请求信息
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes != null ? attributes.getRequest() : null;
        
        // 获取方法签名和注解
        MethodSignature signature = (MethodSignature) point.getSignature();
        SystemLog systemLog = signature.getMethod().getAnnotation(SystemLog.class);
        
        // 构建日志对象
        Log logEntity = new Log();
        logEntity.setLogId(UUID.randomUUID().toString().replace("-", ""));
        logEntity.setType(systemLog.type());
        logEntity.setTitle(systemLog.title());
        
        if (request != null) {
            logEntity.setRemoteAddr(getRemoteAddr(request));
            logEntity.setRequestUri(request.getRequestURI());
            logEntity.setMethod(request.getMethod());
            
            // 获取当前登录用户
            String userId = getCurrentUserId(request);
            logEntity.setUserId(userId);
            
            // 记录请求参数
            if (systemLog.recordParams()) {
                Object[] args = point.getArgs();
                if (args != null && args.length > 0) {
                    try {
                        // 过滤掉 HttpServletRequest/Response 等对象
                        StringBuilder params = new StringBuilder();
                        for (Object arg : args) {
                            if (arg instanceof HttpServletRequest || arg instanceof javax.servlet.http.HttpServletResponse) {
                                continue;
                            }
                            params.append(JSON.toJSONString(arg)).append(",");
                        }
                        if (params.length() > 0) {
                            params.setLength(params.length() - 1);
                        }
                        logEntity.setParams(params.toString());
                    } catch (Exception e) {
                        logEntity.setParams("参数序列化失败");
                    }
                }
            }
        }
        
        logEntity.setOperateDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        
        Object result = null;
        try {
            // 执行目标方法
            result = point.proceed();
            logEntity.setException(null);
            return result;
        } catch (Throwable e) {
            // 记录异常信息
            logEntity.setException(e.getMessage() != null ? e.getMessage() : e.getClass().getSimpleName());
            log.error("操作异常: {}", e.getMessage(), e);
            throw e;
        } finally {
            // 计算耗时
            long endTime = System.currentTimeMillis();
            logEntity.setTimeout(String.valueOf(endTime - startTime));
            
            // 保存日志
            try {
                logMapper.insert(logEntity);
            } catch (Exception e) {
                log.error("保存操作日志失败: {}", e.getMessage());
            }
        }
    }
    
    /**
 * 获取客户端 IP 地址
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
            ip = request.getHeader("X-Real-IP");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        // 多个代理时取第一个 IP
        if (ip != null && ip.contains(",")) {
            ip = ip.split(",")[0].trim();
        }
        return ip;
    }
    
    /**
 * 获取当前登录用户 ID
 */
    private String getCurrentUserId(HttpServletRequest request) {
        // 从 SecurityContext 获取
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof String) {
                return (String) principal;
            }
        }
        
        // 从 JWT Token 获取
        String token = request.getHeader("Authorization");
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
            try {
                Long userId = jwtUtils.getUserId(token);
                return userId != null ? userId.toString() : "anonymous";
            } catch (Exception e) {
                log.warn("解析JWT Token失败: {}", e.getMessage());
            }
        }
        
        return "anonymous";
    }
}