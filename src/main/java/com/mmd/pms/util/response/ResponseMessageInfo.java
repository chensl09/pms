package com.mmd.pms.util.response;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 返回的提示字符串配置
 * 定义返回类型错误码提示字符串
 * 可继续添加
 * 返回状态码在ResponseUtil中
 */
@Component
public class ResponseMessageInfo {

    @Value("#{messages.success}")
    private String success;

    @Value("#{messages.toLogin}")
    private String toLogin;

    @Value("#{messages.error}")
    private String error;

    public String getSuccess() {
        return success;
    }

    public String getToLogin() {
        return toLogin;
    }

    public String getError() {
        return error;
    }

}
