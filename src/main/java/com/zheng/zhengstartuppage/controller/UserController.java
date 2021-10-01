package com.zheng.zhengstartuppage.controller;

import com.zheng.zhengstartuppage.entity.user.UserDataEntity;
import com.zheng.zhengstartuppage.entity.user.UserEntity;
import com.zheng.zhengstartuppage.util.AppResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : 陈征
 * @date : 2021-09-30 21:03
 */

@RestController
@RequestMapping("/user")
public class UserController {

//    @PostMapping("/login")
    @GetMapping("/login")
    public AppResult login() {
        return AppResult.success();
    }


}
