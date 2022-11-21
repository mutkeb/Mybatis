package com.youkeda.comment.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class Result<D> implements Serializable {
    //  表示执行成功或失败
    @JsonProperty("isSuccess")
    private boolean success = false;

    //  返回消息短码，一般用于错误时，简短描述错误
    private String code;

    //  详细错误
    private String message;

    //  返回具体数据
    private D data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public D getData() {
        return data;
    }

    public void setData(D data) {
        this.data = data;
    }
}
