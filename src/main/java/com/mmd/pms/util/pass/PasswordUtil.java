package com.mmd.pms.util.pass;

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
    public static String buildPassword(String password, String salt) {
        return MD5Util.md5Encode(MD5Util.md5Encode(password) + salt);
    }

    /**
     * 生成 token 的方式
     * @param loginName
     * @param password
     * @return
     */
    public static String buildToken(String loginName, String password){
        return buildPassword(MD5Util.md5Encode(loginName+password), MD5Util.md5Encode(password));
    }

}
