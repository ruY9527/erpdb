package com.erp.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 操作日志实体
 *
 * @author baoyang
 */
@TableName("mylog")
public class Log {

    @TableId("logId")
    private String logId;

    private String type;
    private String title;
    private String remoteAddr;
    private String requestUri;
    private String method;
    private String params;
    private String exception;
    private String operateDate;
    private String timeout;
    private String userId;

    @TableField(exist = false)
    private Emp emp;

    public String getLogId() { return logId; }
    public void setLogId(String logId) { this.logId = logId; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getRemoteAddr() { return remoteAddr; }
    public void setRemoteAddr(String remoteAddr) { this.remoteAddr = remoteAddr; }
    public String getRequestUri() { return requestUri; }
    public void setRequestUri(String requestUri) { this.requestUri = requestUri; }
    public String getMethod() { return method; }
    public void setMethod(String method) { this.method = method; }
    public String getParams() { return params; }
    public void setParams(String params) { this.params = params; }
    public String getException() { return exception; }
    public void setException(String exception) { this.exception = exception; }
    public String getOperateDate() { return operateDate; }
    public void setOperateDate(String operateDate) { this.operateDate = operateDate; }
    public String getTimeout() { return timeout; }
    public void setTimeout(String timeout) { this.timeout = timeout; }
    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }
    public Emp getEmp() { return emp; }
    public void setEmp(Emp emp) { this.emp = emp; }
}