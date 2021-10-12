package com.zheng.zhengstartuppage.entity.user;

import com.zheng.zhengstartuppage.entity.BaseEntity;
import lombok.Data;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * @author : 陈征
 * @date : 2021-09-30 20:21
 */

@Component
@Data
public class UserDataEntity extends BaseEntity {

    long id;

    long userId;

    @Pattern(regexp = "[^;\u0020,:\"'%$()&]*")
    String nickname;

    @Pattern(regexp = "[^;\u0020,:\"'%$()&]*")
    String searchEngine;

    @Pattern(regexp = "[^;\u0020,:\"'%$()&]*")
    String cityName;

    int secondDisplay;

    int catDisplay;

    long lastLoginTime;

    // 用于更新 userdata 时验证
    @NotNull
    @Pattern(regexp = "[0-9a-zA-Z]{4,16}")
    String password;

    public UserDataEntity() {
    }

    public UserDataEntity(long userId, String nickname, long lastLoginTime) {
        this.userId = userId;
        this.nickname = nickname;
        this.lastLoginTime = lastLoginTime;
    }
}
