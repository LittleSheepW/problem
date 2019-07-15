package com.ww.api;

/**
 * API 请求返回状态码
 */
public enum ApiStatus {

    /**
     * 异常情况
     */
    RUNTIME_ERROR(-1, "本次操作过程中出现异常，请重试"),

    /**
     * 操作成功
     */
    OK(0, "成功"),

    /**
     * 学生相关状态码
     */
    STUDENT_DOES_NOT_EXIST(101, "该学生不存在"),

    ;

    private int code;
    private String msg;

    ApiStatus(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
