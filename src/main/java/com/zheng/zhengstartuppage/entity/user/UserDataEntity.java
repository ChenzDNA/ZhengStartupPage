package com.zheng.zhengstartuppage.entity.user;

import com.zheng.zhengstartuppage.entity.BaseEntity;
import com.zheng.zhengstartuppage.entity.CollectionEntity;
import com.zheng.zhengstartuppage.entity.NoteEntity;
import com.zheng.zhengstartuppage.entity.TodoEntity;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

/**
 * @author : 陈征
 * @date : 2021-09-30 20:21
 */

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

    private List<CollectionEntity> collections;

    private List<NoteEntity> notes;

    private List<TodoEntity> todos;

    public UserDataEntity() {
    }

    public UserDataEntity(long userId, String nickname, long lastLoginTime) {
        this.userId = userId;
        this.nickname = nickname;
        this.lastLoginTime = lastLoginTime;
    }
}
