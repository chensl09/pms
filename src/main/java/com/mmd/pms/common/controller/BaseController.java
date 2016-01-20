package com.mmd.pms.common.controller;

import com.mmd.pms.common.ResponseModel;
import com.mmd.pms.common.model.RequestHeaderModel;
import com.mmd.pms.util.response.ResponseMessageInfo;
import com.mmd.pms.util.response.ResponseUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;

/**
 * 控制器父类
 * 还可以添加其它的公用的方法在这个超类中
 */
public class BaseController {

    /**
     * 日志对象
     */
    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    protected ResponseMessageInfo responseMessageInfo;

    /**
     * 得到请求头中的token信息
     * @param request
     * @return
     */
    @ModelAttribute
    public RequestHeaderModel buildRequest(HttpServletRequest request ) {

        RequestHeaderModel requestHeaderModel = new RequestHeaderModel();
        String pmstoken = request.getHeader("pmstoken");

        if(pmstoken != null){
            requestHeaderModel.setPmstoken(pmstoken);
        }

        return requestHeaderModel;
    }

    /**
     * 带数据返回成功
     * @param object
     * @return
     */
    protected ResponseModel setModelSuccessWithData(Object object){
        return ResponseUtils.setModelSuccessWithData(object, responseMessageInfo.getSuccess());
    }

    /**
     * 不带数据返回成功
     * @return
     */
    protected ResponseModel setModelSuccessNoData(){
        return ResponseUtils.setModelSuccessNoData(responseMessageInfo.getSuccess());
    }

    /**
     * 提示用户去登录
     * @return
     */
    protected ResponseModel setModelToLogin(){
        return ResponseUtils.setModelToLogin(responseMessageInfo.getToLogin());
    }

    /**
     * 带数据的操作失败返回
     * @param object
     * @return
     */
    protected ResponseModel setModelErrorWithData(Object object){
        return ResponseUtils.setModelErrorWithData(object, responseMessageInfo.getError());
    }

    /**
     * 不带数据的操作失败返回
     * @return
     */
    protected ResponseModel setModelErrorNoData(){
        return ResponseUtils.setModelSuccessNoData(responseMessageInfo.getError());
    }

}
