package com.mmd.pms.user.service.impl;

import com.mmd.pms.common.service.impl.BaseServiceImpl;
import com.mmd.pms.user.dao.UserDao;
import com.mmd.pms.user.entity.User;
import com.mmd.pms.user.service.UserService;
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
        User user = new User();
        user.setLoginName(loginName);
        user.setUserType(userType);
        return mapper.queryUserByLoginName(user);
    }

}
