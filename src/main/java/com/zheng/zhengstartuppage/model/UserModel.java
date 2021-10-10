package com.zheng.zhengstartuppage.model;

import com.zheng.zhengstartuppage.dao.UserDao;
import com.zheng.zhengstartuppage.entity.user.UserDataEntity;
import com.zheng.zhengstartuppage.entity.user.UserEntity;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author : 陈征
 * @date : 2021-09-30 20:27
 */

@Repository
public class UserModel {
    @Resource
    private UserDao userDao;

    public UserEntity getUserById(long id) {
        return userDao.getUser(id);
    }

    public UserEntity getUserByAccount(String account) {
        return userDao.getUserByAccount(account);
    }

    public UserDataEntity getUserDataByUserId(long userId) {
        return userDao.getUserDataByUser(userId);
    }

    public boolean hasUser(String account) {
        return userDao.getUserByAccount(account) != null;
    }

    public boolean hasUser(long id) {
        return userDao.getUser(id) != null;
    }

    public long insertUser(UserEntity userEntity) {
        userEntity.setCtime(new Date().getTime());
        return userDao.insertUser(userEntity);
    }

    public long insertUserData(UserDataEntity userDataEntity) {
        userDataEntity.setCtime(new Date().getTime());
        return userDao.insertUserData(userDataEntity);
    }

    public void updateUserLastLoginTime(long userId) {
        UserDataEntity userDataEntity = new UserDataEntity();
        userDataEntity.setUserId(userId);
        long now = new Date().getTime();
        userDataEntity.setLastLoginTime(now);
        userDataEntity.setMtime(now);
        userDao.updateUserDataByUserId(userDataEntity);
    }
}
