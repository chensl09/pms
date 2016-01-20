package com.mmd.pms.user.controller;

import com.mmd.pms.common.controller.BaseController;
import com.mmd.pms.common.model.RequestHeaderModel;
import com.mmd.pms.user.entity.User;
import com.mmd.pms.user.service.UserService;
import com.mmd.pms.util.pass.PasswordUtil;
import com.mmd.pms.util.response.ResponseMessageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.support.RequestContext;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * 用户控制Controller层
 */

@Controller
@RequestMapping("/user")
public class UserController extends BaseController{

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> login(String loginName, String password,
                                     RequestHeaderModel headerModel, HttpServletRequest request){


        logger.error("pmstoken = " + headerModel.getPmstoken());

        //从后台代码获取国际化信息
       /* RequestContext requestContext = new RequestContext(request);
        String message = requestContext.getMessage("success");
        logger.info("message = " + message);*/

        logger.info("\nsuccess = " + responseMessageInfo.getSuccess());

        Map<String, Object> resData = new HashMap<String, Object>();

        User user = userService.queryUserByLoginName(loginName, User.UserType.customer.getValue());

        try {
            String pass = PasswordUtil.buildPassword(user.getPassword(), user.getSalt());
            logger.info("buildPassword: " + pass);
        } catch (UnsupportedEncodingException e) {
            logger.error("报错了哦...", e);
            e.printStackTrace();
        }

        resData.put("user", user);

        return resData;
    }

}
