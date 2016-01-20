package com.mmd.pms.user.service;

import com.mmd.pms.common.service.BaseService;
import com.mmd.pms.user.dao.UserDao;
import com.mmd.pms.user.entity.User;

/**
 * 用户服务接口类
 */
public interface UserService extends BaseService<UserDao, User> {

    /**
     * 根据登录名来查找用户信息
     * @param loginName
     * @param userType
     * @return
     */
    User queryUserByLoginName (String loginName, Integer userType);

}
