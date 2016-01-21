package com.mmd.pms.common.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 返回数据的封装类
 */
public class ResponseModel {

    private Integer code;//返回的状态码

    /**
     * 指定返回的key为data, 为null时不返回
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @JsonProperty(value = "data")
    private Object object;//返回的数据

    private String message;//返回的提示信息


    public ResponseModel() {
    }

    public ResponseModel(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResponseModel(Integer code, Object object, String message) {
        this.code = code;
        this.object = object;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
