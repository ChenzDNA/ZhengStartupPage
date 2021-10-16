package com.zheng.zhengstartuppage.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Pattern;

/**
 * @author : 陈征
 * @date : 2021-09-30 20:22
 */

@Component
@Data
public class NoteEntity extends BaseEntity{
    long id;

    long userId;

    String content;

    String title;
}
