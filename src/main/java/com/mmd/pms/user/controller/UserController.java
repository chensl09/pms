package com.mmd.pms.user.controller;

import com.mmd.pms.common.controller.BaseController;
import com.mmd.pms.common.model.RequestHeaderModel;
import com.mmd.pms.user.entity.User;
import com.mmd.pms.user.service.UserService;
import com.mmd.pms.util.StringUtils;
import com.mmd.pms.util.pass.PasswordUtil;
import com.mmd.pms.util.response.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

/**
 * 用户控制Controller层
 */

@Controller
@RequestMapping("/user")
public class UserController extends BaseController{

    @Autowired
    private UserService userService;

    @RequestMapping(value = {"/", "/list"}, method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> list(){
        List<User> userList = userService.queryList(new User());
        return ResponseUtils.buildSuccessObject(userList, responseMessageInfo.getSuccess());
    }


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> login(@Valid User user,
                                     BindingResult result,
                                     RequestHeaderModel headerModel, HttpServletRequest request){

        logger.error("pmstoken = " + headerModel.getPmstoken());

        if(result.hasErrors()){

            logger.info(result.getFieldError("loginName").getDefaultMessage());
            List<ObjectError> list = result.getAllErrors();
            for (int i = 0; i < list.size(); i++) {
                ObjectError error = list.get(i);
                logger.info("error" + i + " = " + error.getArguments()+"=="+error.getDefaultMessage());
            }

            //如果严重没有通过,跳转提示
            return ResponseUtils.buildSuccessObject(result.getAllErrors(), responseMessageInfo.getError());
        }

        //从后台代码获取国际化信息
       /* RequestContext requestContext = new RequestContext(request);
        String message = requestContext.getMessage("success");
        logger.info("message = " + message);*/
        logger.info("\nsuccess = " + responseMessageInfo.getSuccess());
        User user2 = userService.queryUserByLoginName(user.getLoginName(), User.UserType.customer.getValue());
        try {
            String pass = PasswordUtil.buildPassword(user2.getPassword(), user2.getSalt());
            logger.info("buildPassword: " + pass);
        } catch (UnsupportedEncodingException e) {
            logger.error("报错了哦...", e);
            e.printStackTrace();
        }

        return ResponseUtils.buildSuccessObject(user2, responseMessageInfo.getSuccess());
    }


    @RequestMapping("/reg")
    @ResponseBody
    public Map<String, Object> reg(@Valid User user) throws Exception{

        String salt = StringUtils.buildSalt();
        user.setSalt(salt);
        user.setPassword(PasswordUtil.buildPassword(user.getPassword(), salt));
        user.setUserType(User.UserType.customer.getValue());

        userService.saveOrUpdate(user);
        return ResponseUtils.buildSuccessObject(null, responseMessageInfo.getSuccess());
    }

}
