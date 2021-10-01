package com.zheng.zhengstartuppage.entity.user;

import com.zheng.zhengstartuppage.entity.BaseEntity;
import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @author : 陈征
 * @date : 2021-09-30 20:21
 */

@Component
@Data
public class UserDataEntity extends BaseEntity {

    Long id;

    Long userId;

    String nickname;

    String searchEngine;

    String cityName;

    Integer secondDisplay;

    Integer catDisplay;

    Long lastLoginTime;
}
