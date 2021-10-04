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

    @Pattern(regexp = "[^;\u0020\s,:\\\"\\\'$%\\$\\(\\)&]*")
    String nickname;

    @Pattern(regexp = "[^;\u0020\s,:\\\"\\\'$%\\$\\(\\)&]*")
    String searchEngine;

    @Pattern(regexp = "[^;\u0020\s,:\\\"\\\'$%\\$\\(\\)&]*")
    String cityName;

    int secondDisplay;

    int catDisplay;

    long lastLoginTime;
}
