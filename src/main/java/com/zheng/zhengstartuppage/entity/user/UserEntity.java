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
public class UserEntity extends BaseEntity {

    long id;

    @NotNull
    @Pattern(regexp = "[0-9a-zA-Z]{4,16}")
    String account;

    @NotNull
    @Pattern(regexp = "[0-9a-zA-Z]{4,16}")
    String password;
}
