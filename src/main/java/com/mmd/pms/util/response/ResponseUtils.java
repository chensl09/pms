package com.mmd.pms.util.response;

import com.mmd.pms.common.model.ResponseModel;

import java.util.HashMap;
import java.util.Map;

/**
 * 返回json数据时用统一的格式
 */
public class ResponseUtils {

    public enum ResponseCode{
        success(0),//返回成功
        error(1),//返回失败
        toLogin(-1);//未登录

        private int value;

        private ResponseCode(int value){
            this.value = value;
        }

        public int getValue(){
            return value;
        }

    }


    /**
     * 一切正常的情况下使用
     * @param resData
     * @param message 提示信息
     *
     * @return
     */
    public static Map<String, Object> buildSuccessMap(Map<String, Object> resData, String message){
        resData.put("code", ResponseCode.success.getValue());
        resData.put("message", message);
        return resData;
    }

    /**
     * 获取某对象成功时使用
     * @param object
     * @param message
     * @return
     */
    public static Map<String, Object> buildSuccessObject(Object object, String message){
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("list", object);
        resMap.put("code", ResponseCode.success.getValue());
        resMap.put("message", message);
        return resMap;
    }

    /**
     * 提示用户登录
     * @param message
     * @return
     */
    public static Map<String, Object> buildToLoginMap(String message){
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("code", ResponseCode.toLogin.getValue());
        resMap.put("message", message);
        return resMap;
    }

    /**
     * 操作失败
     * @param message
     * @return
     */
    public static Map<String, Object> buildErrorMap(String message){
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("code", ResponseCode.error.getValue());
        resMap.put("message", message);
        return resMap;
    }

    /**
     * 有数据的成功操作
     * @param object
     * @param message
     * @return
     */
    public static ResponseModel setModelSuccessWithData(Object object, String message){
        return new ResponseModel(ResponseCode.success.getValue(), object, message);
    }

    /**
     * 无返回数据的成功操作
     * @param message
     * @return
     */
    public static ResponseModel setModelSuccessNoData(String message){
        return new ResponseModel(ResponseCode.success.getValue(), message);
    }

    /**
     * 提示用户登录
     * @param message
     * @return
     */
    public static ResponseModel setModelToLogin(String message){
        return new ResponseModel(ResponseCode.toLogin.getValue(), message);
    }

    /**
     * 操作失败,且无数据返回
     * @param message
     * @return
     */
    public static ResponseModel setModelErrorNoData(String message){
        return new ResponseModel(ResponseCode.error.getValue(), message);
    }

    /**
     * 有数据返回的失败操作
     * @param message
     * @return
     */
    public static ResponseModel setModelErrorWithData(Object object, String message){
        return new ResponseModel(ResponseCode.error.getValue(), object, message);
    }
}
