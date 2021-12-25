package com.zheng.zhengstartuppage.entity;

import lombok.Data;

import javax.validation.constraints.Pattern;

/**
 * @author : 陈征
 * @date : 2021-09-30 20:22
 */

@Data
public class TodoEntity extends BaseEntity {
    long id;

    long userId;

    @Pattern(regexp = "[^\s<>]*")
    String content;

    int finished;
}
