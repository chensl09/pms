package com.mmd.pms.user.controller;

import com.mmd.pms.common.controller.BaseController;
import com.mmd.pms.user.entity.User;
import com.mmd.pms.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
    public Map<String, Object> login(String loginName, String password){

        Map<String, Object> resData = new HashMap<String, Object>();
        resData.put("user", userService.queryUserByLoginName(loginName, User.UserType.customer.getValue()));

        return resData;
    }

}
