package com.mmd.pms.util.pass;

import java.io.UnsupportedEncodingException;

/**
 * 密码加密工具
 */
public class PasswordUtil {

    /**
     * 用户登录等密码的加密方法
     * @param password
     * @param salt
     * @return
     */
    public static String buildPassword(String password, String salt) throws UnsupportedEncodingException {
        return MD5Util.md5Encode(MD5Util.md5Encode(password) + salt);
    }

}
