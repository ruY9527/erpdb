package com.erp.pojo;

/**
 * EasyUI 返回结果
 *
 * @author baoyang
 */
public class EasyResult {
    private boolean success;
    private String message;

    public EasyResult() {}
    public EasyResult(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() { return success; }
    public void setSuccess(boolean success) { this.success = success; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}