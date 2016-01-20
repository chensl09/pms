package com.mmd.pms.constants;

/**
 * 用于缓存的常量 枚举
 */
public class ConstantForCache {

    //用户的缓存前缀字符串定义
    public enum UserCache{
        userCache("userCache"),
        userId("userId_"),
        loginName("loginName_");

        private String value;

        private UserCache(String value){
            this.value = value;
        }

        public String getValue(){
            return value;
        }

    }

    /**
     * 图片缓存前缀字符串定义
     */
    public enum PictureCache{
        pictureCache("pictureCache"),
        pictureId("pictureId_");

        private String value;

        private PictureCache(String value){
            this.value = value;
        }

        public String getValue(){
            return value;
        }
    }

}
