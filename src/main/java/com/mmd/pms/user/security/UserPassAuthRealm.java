package com.mmd.pms.user.security;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 *
 */
@Component
public class UserPassAuthRealm extends AuthorizingRealm{

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

        //todo 添加逻辑

        return info;
    }

    /**
     * 进行登陆的认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
            throws AuthenticationException {
        UsernamePasswordToken authTocken = (UsernamePasswordToken)authenticationToken;

        //todo 添加逻辑

        return new SimpleAuthenticationInfo();
    }

    /**
     * 设定密码校验的算法与迭代次数
     */
    @PostConstruct
    public void initCredentialsMatcher () {

    }
}
