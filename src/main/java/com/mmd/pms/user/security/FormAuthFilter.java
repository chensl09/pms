package com.mmd.pms.user.security;

import com.mmd.pms.util.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.stereotype.Component;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * 表单验证的过滤器
 */
@Component
public class FormAuthFilter extends FormAuthenticationFilter {

    public static final String MESSAGE_PARAM = "message";

    private String messageParam = MESSAGE_PARAM;

    public String getMessageParam() {
        return messageParam;
    }

    @Override
    protected boolean onLoginFailure(AuthenticationToken token,
                                     AuthenticationException e,
                                     ServletRequest request,
                                     ServletResponse response) {
        String message;
        if (e instanceof IncorrectCredentialsException
                || e instanceof UnknownAccountException) {
            message = "用户名或密码不正确";
        } else if (e.getMessage() != null && e.getMessage().startsWith("pms:")) {
            message = StringUtils.replace(e.getMessage(), "pms:", "");
        } else {
            message = "服务器内部错误，请稍后再试";
        }
        request.setAttribute(messageParam, message);
        return true;
    }

    @Override
    protected void issueSuccessRedirect(ServletRequest request, ServletResponse response) throws Exception {
        String successUrl = getSuccessUrl();
        WebUtils.issueRedirect(request, response, successUrl, null, true);
    }
}
