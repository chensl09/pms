package com.mmd.pms.common.controller;

import com.mmd.pms.common.model.RequestHeaderModel;
import com.mmd.pms.util.response.ResponseMessageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;

/**
 * 控制器父类
 */
public class BaseController {

    /**
     * 日志对象
     */
    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    protected ResponseMessageInfo responseMessageInfo;

    @ModelAttribute
    public RequestHeaderModel buildRequest(HttpServletRequest request ) {

        RequestHeaderModel requestHeaderModel = new RequestHeaderModel();
        String pmstoken = request.getHeader("pmstoken");

        if(pmstoken != null){
            requestHeaderModel.setPmstoken(pmstoken);
        }

        return requestHeaderModel;
    }

}
