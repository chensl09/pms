package com.mmd.pms.util;

import com.mmd.pms.common.SpringContextHolder;
import com.mmd.pms.user.entity.User;
import com.mmd.pms.user.security.Principal;
import com.mmd.pms.user.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

/**
 * 用户信息帮助类
 */
public class UserUtils {

    private static UserService userService = SpringContextHolder.getBean(UserService.class);


    public static final String CACHE_ROLE_LIST = "user_role_list";
    public static final String CACHE_MENU_LIST = "user_menu_list";


    /**
     * 获取当前登陆的用户信息
     * @return
     */
    public static Principal getPrincipal () {
        Subject subject = SecurityUtils.getSubject();
        Principal principal =  (Principal)subject.getPrincipal();
        return principal;
    }

    /**
     * 获取当前登陆的用户对象
     * @return 如果登陆的用户为空，则返回空User
     */
    public static User getCurrentUser () {
        Principal principal = getPrincipal();
        if (principal != null) {
            User user = userService.queryById(principal.getId());
            if (user != null) {
                return user;
            } else {
                return new User ();
            }
        } else {
            return new User ();
        }
    }

    /**
     * 获取当前登陆用户所拥有的菜单列表
     * @return
     */


    /**
     * 获取当前登陆的用户的角色列表
     * @return
    */


    /**
     * 获得用户的会话信息
     * @return
     */
    public static Session getSession () {
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        return session;
    }

    //--------------------------用户会话信息的缓存操作-----------------------//
    public static void putCache (String key, Object value) {
        getSession().setAttribute(key, value);
    }

    public static Object getCache (String key) {
        return getSession().getAttribute(key);
    }

    public static void removeCache (String key) {
        getSession().removeAttribute(key);
    }
}
