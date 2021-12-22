package com.zheng.zhengstartuppage.service;

import com.zheng.zhengstartuppage.entity.user.UserDataEntity;
import com.zheng.zhengstartuppage.entity.user.UserEntity;
import com.zheng.zhengstartuppage.exception.IllegalResultClassException;
import com.zheng.zhengstartuppage.model.UserModel;
import com.zheng.zhengstartuppage.util.SessionUtil;
import com.zheng.zhengstartuppage.util.enums.EnumUtil;
import com.zheng.zhengstartuppage.util.enums.SearchEngineEnum;
import com.zheng.zhengstartuppage.util.returns.ReturnsData;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import java.util.Date;

/**
 * @author : 陈征
 * @date : 2021-10-01 21:55
 */

@Service
public class UserService {
    @Resource
    private UserModel userModel;

    @Resource
    private RedisTemplate<Object, Object> redisTemplate;

    public ReturnsData loginService(UserEntity userEntity) throws IllegalBlockSizeException, BadPaddingException {
        if (!userModel.hasUser(userEntity.getAccount())) {
            return ReturnsData.returns("用户不存在：" + userEntity.getAccount());
        }
        UserEntity realUser = userModel.getUserByAccount(userEntity.getAccount());
        if (!BCrypt.checkpw(userEntity.getPassword(), realUser.getPassword())) {
            return ReturnsData.returns("密码错误");
        }
        SessionUtil.setUser(realUser);

        UserDataEntity userDataEntity = userModel.getUserDataByUserId(realUser.getId());
        userModel.updateUserLastLoginTime(realUser.getId());

        return ReturnsData.returns(realUser, userDataEntity);
    }

    public ReturnsData registerService(UserEntity userEntity) throws IllegalResultClassException, IllegalBlockSizeException, BadPaddingException {
        int remainTimes = (Integer) redisTemplate.opsForValue().get(SessionUtil.getIp());
        if (remainTimes < 1) {
            return ReturnsData.returns("过一段时间在来吧");
        }
        redisTemplate.opsForValue().set(SessionUtil.getIp(), remainTimes - 1);
        if (userModel.hasUser(userEntity.getAccount())) {
            return ReturnsData.returns("已存在账号。");
        }
        userEntity.setPassword(BCrypt.hashpw(userEntity.getPassword(), BCrypt.gensalt()));
        userModel.insertUser(userEntity);
        SessionUtil.setUser(userEntity);

        UserDataEntity userDataEntity = new UserDataEntity(userEntity.getId(), "新用户" + userEntity.getId(), new Date().getTime());
        userModel.insertUserData(userDataEntity);

        return ReturnsData.returns(userEntity, userDataEntity);
    }

    public UserDataEntity getUserDataByUserId(long userId) {
        return userModel.getUserDataByUserId(userId);
    }

    public void updateUserLastLoginTime(long userId) {
        userModel.updateUserLastLoginTime(userId);
    }

    public ReturnsData updateUserData(UserDataEntity userDataEntity) throws IllegalResultClassException {
        userDataEntity.setUserId(SessionUtil.getUser().getId());
        if (!BCrypt.checkpw(userDataEntity.getPassword(), SessionUtil.getUser().getPassword())) {
            return ReturnsData.returns("密码验证错误");
        }
        if (!EnumUtil.verify(SearchEngineEnum.class, userDataEntity.getSearchEngine())) {
            return ReturnsData.returns("搜索引擎验证错误");
        }
        userModel.updateUserData(userDataEntity);
        return ReturnsData.returns(userDataEntity);
    }
}
