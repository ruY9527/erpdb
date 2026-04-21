package com.erp.utils;

/**
 * 统一返回结果封装
 *
 * @author baoyang
 */
public class MyResult {
    private boolean success;
    private String message;

    public MyResult() {}

    public MyResult(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() { return success; }
    public void setSuccess(boolean success) { this.success = success; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    /**
 * 成功结果
 */
    public static MyResult success() {
        return new MyResult(true, "操作成功");
    }

    /**
 * 成功结果带消息
 */
    public static MyResult success(String message) {
        return new MyResult(true, message);
    }

    /**
 * 失败结果
 */
    public static MyResult fail() {
        return new MyResult(false, "操作失败");
    }

    /**
 * 失败结果带消息
 */
    public static MyResult fail(String message) {
        return new MyResult(false, message);
    }
}