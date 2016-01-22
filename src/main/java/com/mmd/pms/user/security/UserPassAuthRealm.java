package com.mmd.pms.user.security;

import com.mmd.pms.user.entity.User;
import com.mmd.pms.user.service.UserService;
import com.mmd.pms.util.Config;
import com.mmd.pms.util.EncryptUtil;
import com.mmd.pms.util.pass.PasswordUtil;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 *
 */
@Component
public class UserPassAuthRealm extends AuthorizingRealm{

    @Autowired
    private UserService userService;

    /***
     * 获取授权信息
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        Principal principal = (Principal)getAvailablePrincipal(principalCollection);
        //添加当前用户相关的权限信息
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        //todo 添加逻辑,目前只是一个测试，需要关联数据库设计 用户角色与权限
        User user = userService.queryUserByLoginName(principal.getLoginName(), User.UserType.customer.getValue());

        if(user != null){
            info.addStringPermission("user");
            info.addRole("客户");
            return info;
        }

        return null;
    }

    /**
     * kwkw
     * 进行登陆的认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
            throws AuthenticationException {
        UsernamePasswordToken authTocken = (UsernamePasswordToken)authenticationToken;

       User user = userService.queryUserByLoginName(authTocken.getUsername(), User.UserType.customer.getValue());

       if (user != null) {
           if (Config.YES.equals(user.getLoginFlag())) {

               //得到输入的用户名
               String username = (String)authTocken.getUsername();

               //得到输入的密码
               String password = new String(authTocken.getPassword());

               String salt = user.getSalt();

               String inputPass = PasswordUtil.buildPassword(password, salt);

               if(!inputPass.equals(user.getPassword())) {
                   //如果密码错误
                   throw new IncorrectCredentialsException();
               }

               //如果身份认证验证成功，返回一个AuthenticationInfo实现；
               // //todo 添加逻辑 登录成功则修改当前登录状态与登录的信息
               return new SimpleAuthenticationInfo(new Principal(user),
                       password,ByteSource.Util.bytes(salt), getName());

           } else {
               //todo 增加一个对这种类型异常的捕获
               throw new AuthenticationException("pms:你已在其他地点登陆!");
           }

       } else {
           //如果用户名不存在  UnauthorizedException
           throw new UnknownAccountException();
       }

    }


}
