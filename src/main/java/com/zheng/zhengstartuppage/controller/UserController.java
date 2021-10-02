package com.zheng.zhengstartuppage.controller;

import com.zheng.zhengstartuppage.entity.user.UserDataEntity;
import com.zheng.zhengstartuppage.entity.user.UserEntity;
import com.zheng.zhengstartuppage.util.AppResult;
import com.zheng.zhengstartuppage.util.CookieUtil;
import com.zheng.zhengstartuppage.util.SessionUtil;
import com.zheng.zhengstartuppage.util.annotation.LoginMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * @author : 陈征
 * @date : 2021-09-30 21:03
 */

@RestController
@RequestMapping("/user")
public class UserController {

    @LoginMethod
    @PostMapping("/login")
    public AppResult login(HttpServletResponse response, String account, String password) {
        UserEntity user = new UserEntity();
        user.setAccount("asdf");
        user.setPassword("123");
        SessionUtil.setUser(user);
        response.addCookie(CookieUtil.generateUserCookie());
        return AppResult
                .success()
                .grab(new UserEntity())
                .grab(new UserDataEntity());
    }

    @PostMapping("/register")
    public AppResult register(String account, String password) {
        return null;
    }

}
