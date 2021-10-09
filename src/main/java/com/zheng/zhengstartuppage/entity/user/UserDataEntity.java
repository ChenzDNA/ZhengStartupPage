package com.zheng.zhengstartuppage.entity.user;

import com.zheng.zhengstartuppage.entity.BaseEntity;
import lombok.Data;
import org.springframework.stereotype.Component;

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

    @Pattern(regexp = "[^;\u0020\s,:\\\"\\\'$%\\$\\(\\)&]{6,16}")
    String nickname;

    @Pattern(regexp = "[^;\u0020\s,:\\\"\\\'$%\\$\\(\\)&]{6,16}")
    String searchEngine;

    @Pattern(regexp = "[^;\u0020\s,:\\\"\\\'$%\\$\\(\\)&]{6,16}")
    String cityName;

    int secondDisplay;

    int catDisplay;

    long lastLoginTime;

    public UserDataEntity() {
    }

    public UserDataEntity(long userId, String nickname, long lastLoginTime) {
        this.userId = userId;
        this.nickname = nickname;
        this.lastLoginTime = lastLoginTime;
    }
}
