package com.mmd.pms.user.controller;

import com.github.pagehelper.PageInfo;
import com.mmd.pms.common.model.ResponseModel;
import com.mmd.pms.common.controller.BaseController;
import com.mmd.pms.common.model.RequestHeaderModel;
import com.mmd.pms.common.page.PageParam;
import com.mmd.pms.user.entity.User;
import com.mmd.pms.user.service.UserService;
import com.mmd.pms.util.StringUtils;
import com.mmd.pms.util.pass.PasswordUtil;
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

/**
 * 用户控制Controller层
 */

@Controller
@RequestMapping("/user")
public class UserController extends BaseController{

    @Autowired
    private UserService userService;

    @RequestMapping(value = {"/list"}, method = RequestMethod.POST)
    @ResponseBody
    public ResponseModel list(){
        List<User> userList = userService.queryList(new User());

        return setModelSuccessWithData(userList);
    }

    @RequestMapping(value = "/queryListWithPage", method = RequestMethod.POST)
    @ResponseBody
    public ResponseModel queryListWithPage(PageParam pageParam){

        PageInfo<User> pageInfo = userService.queryListWithPage(new User(), pageParam);

        return setModelSuccessWithData(pageInfo);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public ResponseModel login(@Valid User user,
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
            return setModelErrorWithData(result.getAllErrors());
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

        return setModelSuccessWithData(user2);
    }


    @RequestMapping("/reg")
    @ResponseBody
    public ResponseModel reg(@Valid User user) throws Exception{

        String salt = StringUtils.buildSalt();
        user.setSalt(salt);
        user.setPassword(PasswordUtil.buildPassword(user.getPassword(), salt));
        user.setUserType(User.UserType.customer.getValue());

        userService.saveOrUpdate(user);

        return setModelSuccessNoData();

    }

}
