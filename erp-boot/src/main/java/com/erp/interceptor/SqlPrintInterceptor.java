package com.erp.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.regex.Matcher;

/**
 * SQL 执行拦截器
 *
 * 打印完整的 SQL 语句（包含参数值），便于调试和问题排查
 *
 * @author baoyang System
 * @version 1.0
 */
@Slf4j
@Intercepts({
    @Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class}),
    @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, org.apache.ibatis.session.RowBounds.class, org.apache.ibatis.session.ResultHandler.class})
})
@Component
public class SqlPrintInterceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
        Object parameter = invocation.getArgs()[1];
        
        BoundSql boundSql = mappedStatement.getBoundSql(parameter);
        Configuration configuration = mappedStatement.getConfiguration();
        
        // 获取 SQL ID
        String sqlId = mappedStatement.getId();
        
        // 获取完整 SQL
        String sql = getSql(configuration, boundSql, sqlId);
        
        long startTime = System.currentTimeMillis();
        
        try {
            // 执行 SQL
            Object result = invocation.proceed();
            
            long endTime = System.currentTimeMillis();
            long duration = endTime - startTime;
            
            // 打印 SQL 执行日志
            if (duration > 500) {
                log.warn("========== 慢SQL预警 (>500ms) ========== [{}]", sqlId);
            }
            log.debug("========== SQL 执行日志 ========== [{}]", sqlId);
            log.debug("SQL语句: {}", sql);
            log.debug("执行耗时: {}ms", duration);
            
            return result;
        } catch (Throwable e) {
            log.error("========== SQL 执行异常 ========== [{}]", sqlId);
            log.error("SQL语句: {}", sql);
            log.error("异常信息: {}", e.getMessage());
            throw e;
        }
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
    }

    /**
 * 获取完整的 SQL 语句（包含参数值）
 *
     * @param configuration MyBatis 配置
     * @param boundSql 绑定的 SQL
     * @param sqlId SQL ID
     * @return 完整的 SQL 语句
 */
    private String getSql(Configuration configuration, BoundSql boundSql, String sqlId) {
        String sql = boundSql.getSql();
        if (sql == null || sql.isEmpty()) {
            return "";
        }
        
        // 处理 SQL 参数
        Object parameterObject = boundSql.getParameterObject();
        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
        
        if (parameterObject == null || parameterMappings == null || parameterMappings.isEmpty()) {
            return sql.replaceAll("\\s+", " ").trim();
        }
        
        TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
        
        if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
            sql = sql.replaceFirst("\\?", getParameterValue(parameterObject));
        } else {
            MetaObject metaObject = configuration.newMetaObject(parameterObject);
            
            for (ParameterMapping parameterMapping : parameterMappings) {
                String propertyName = parameterMapping.getProperty();
                
                if (metaObject.hasGetter(propertyName)) {
                    Object obj = metaObject.getValue(propertyName);
                    sql = sql.replaceFirst("\\?", getParameterValue(obj));
                } else if (boundSql.hasAdditionalParameter(propertyName)) {
                    Object obj = boundSql.getAdditionalParameter(propertyName);
                    sql = sql.replaceFirst("\\?", getParameterValue(obj));
                }
            }
        }
        
        // 格式化 SQL
        return sql.replaceAll("\\s+", " ").trim();
    }

    /**
 * 获取参数值的字符串表示
 *
     * @param obj 参数对象
     * @return 参数值字符串
 */
    private String getParameterValue(Object obj) {
        if (obj == null) {
            return "null";
        }
        
        if (obj instanceof String) {
            return "'" + obj.toString() + "'";
        }
        
        if (obj instanceof Date) {
            DateFormat formatter = DateFormat.getDateTimeInstance(DateFormat.DEFAULT, DateFormat.DEFAULT, Locale.CHINA);
            return "'" + formatter.format((Date) obj) + "'";
        }
        
        if (obj instanceof Number) {
            return obj.toString();
        }
        
        return obj.toString();
    }
}