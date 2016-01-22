package com.mmd.pms.user.security;

import com.mmd.pms.user.entity.User;
import com.mmd.pms.util.pass.MD5Util;
import com.mmd.pms.util.pass.PasswordUtil;

import java.io.Serializable;

/**
 * 授权用户对象
 */
public class Principal implements Serializable {
    private static final long serialVersionUID = -3474237226178990224L;

    private String id;
    private String name;
    private Integer userType;
    private String loginName;
    private String token;
    private String salt;

    public Principal(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.userType = user.getUserType();
        this.loginName = user.getLoginName();
        this.token = PasswordUtil.buildToken(user.getLoginName(), user.getPassword());
        this.salt = user.getSalt();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }
}
