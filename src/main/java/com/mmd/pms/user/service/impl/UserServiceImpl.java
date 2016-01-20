package com.mmd.pms.user.service.impl;

import com.mmd.pms.common.service.impl.BaseServiceImpl;
import com.mmd.pms.constants.ConstantForCache;
import com.mmd.pms.user.dao.UserDao;
import com.mmd.pms.user.entity.User;
import com.mmd.pms.user.service.UserService;
import com.mmd.pms.util.CacheUtil;
import org.springframework.stereotype.Service;

/**
 * 用户服务类接口
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<UserDao, User> implements UserService {

    /**
     * 根据登录名来查找用户信息
     * @param loginName
     * @param userType
     * @return
     */
    @Override
    public User queryUserByLoginName (String loginName, Integer userType) {
        //先从缓存中去拿
        User user = (User) CacheUtil.get(ConstantForCache.UserCache.userCache.getValue(),
                ConstantForCache.UserCache.loginName.getValue() + loginName);

        if(user == null){
            User userTemp = new User();
            userTemp.setLoginName(loginName);
            userTemp.setUserType(userType);

            user = mapper.queryUserByLoginName(userTemp);

            if(user == null){
                return null;
            }

            CacheUtil.put(ConstantForCache.UserCache.userCache.getValue(),
                    ConstantForCache.UserCache.loginName.getValue()+user.getLoginName(),
                    user);

            CacheUtil.put(ConstantForCache.UserCache.userCache.getValue(),
                    ConstantForCache.UserCache.userId.getValue()+user.getId(),
                    user);

        }

        return user;
    }

}
