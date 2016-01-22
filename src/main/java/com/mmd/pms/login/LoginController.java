package com.mmd.pms.login;

import com.mmd.pms.common.controller.BaseController;
import com.mmd.pms.user.security.FormAuthFilter;
import com.mmd.pms.user.security.Principal;
import com.mmd.pms.util.UserUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * 登陆控制器
 */
@Controller
public class LoginController extends BaseController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String toLogin () {
        return "sys/login";
    }


    @RequestMapping (value = "/login", method = RequestMethod.POST)
    public String doLogin(HttpServletRequest request, Model model) {

        String username = WebUtils.getCleanParam(request, FormAuthFilter.DEFAULT_USERNAME_PARAM);
        String password = WebUtils.getCleanParam(request, FormAuthFilter.DEFAULT_PASSWORD_PARAM);
        boolean remenberMe = WebUtils.isTrue(request, FormAuthFilter.DEFAULT_REMEMBER_ME_PARAM);

        //判断用户是否已经登陆
        Principal principal = UserUtils.getPrincipal();
        if (principal != null) {

            //用户名唯一
            if(principal.getLoginName().equals(username)){

                return "redirect:/index";

            }else{
                Subject subject = SecurityUtils.getSubject();

                UsernamePasswordToken token = new UsernamePasswordToken(username, password, remenberMe);

                subject.login(token);

                if(subject.isAuthenticated()){
                    logger.info("登录...");
                    return "redirect:/index";
                }

            }

        }

        String exception = (String)request.getAttribute(FormAuthFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);
        String message = (String) request.getAttribute(FormAuthFilter.MESSAGE_PARAM);


        model.addAttribute(FormAuthFilter.DEFAULT_USERNAME_PARAM, username);
        model.addAttribute(FormAuthFilter.DEFAULT_REMEMBER_ME_PARAM, remenberMe);
        model.addAttribute(FormAuthFilter.MESSAGE_PARAM, message);
        model.addAttribute(FormAuthFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME, exception);

        return "sys/login";
    }

    @RequiresPermissions("user")
    @RequestMapping(value = "/index")
    public String index () {
        return "sys/index";
    }

}
