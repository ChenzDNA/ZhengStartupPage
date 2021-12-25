package com.zheng.zhengstartuppage.entity;

import lombok.Data;

import javax.validation.constraints.Pattern;

/**
 * @author : 陈征
 * @date : 2021-09-30 20:22
 */

@Data
public class CollectionEntity extends BaseEntity {
    long id;

    long userId;

    @Pattern(regexp = "[^\s\n]{1,100}")
    String url;

    @Pattern(regexp = "[^\s\n]{1,20}")
    String name;

    @Pattern(regexp = "[^\s\n]{7}")
    String color;
}
