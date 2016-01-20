package com.mmd.pms.user.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mmd.pms.common.entity.BaseEntity;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.Date;

/**
 * 用户实体类
 */
public class User extends BaseEntity {

    private static final long serialVersionUID = 521939327403960794L;

    public static final String LOGIN_NORMAL = "1";

    public enum UserType{
        customer(1),//客户
        business(2),//商家
        printing(3);//印刷厂商

        private int value;

        private UserType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

    }

    @NotEmpty(message = "{com.mmd.pms.notEmpty}")
    @Length(min = 6,max = 10, message = "{com.mmd.pms.nameLength}")
    private String loginName;
    private String password;
    private String salt;
    private String name;
    @Email(message = "邮箱格式不正确")
    private String email;
    private String phone;
    private String mobile;
    private String loginIp;
    private String loginFlag;
    private Date loginDate;
    private Integer userType;

    public User() {
    }

    public User(String id) {
        super(id);
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @JsonIgnore
    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    public String getLoginFlag() {
        return loginFlag;
    }

    public void setLoginFlag(String loginFlag) {
        this.loginFlag = loginFlag;
    }

    public Date getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(Date loginDate) {
        this.loginDate = loginDate;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }
}
