package com.zheng.zhengstartuppage.entity;

import lombok.Data;

/**
 * @author : 陈征
 * @date : 2021-09-30 20:22
 */

@Data
public class NoteEntity extends BaseEntity{
    long id;

    long userId;

    String content;

    String title;
}
