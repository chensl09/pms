package com.mmd.pms.user.dao;

import com.mmd.pms.common.dao.BaseDao;
import com.mmd.pms.user.entity.User;
import org.springframework.stereotype.Repository;

/**
 * 用户Dao类
 */
@Repository
public interface UserDao extends BaseDao<User> {

    /**
     * 根据用户的用户名查询用户
     * @param user
     * @return
     */
    User queryUserByLoginName (User user);
}
