package com.zheng.zhengstartuppage.controller;

import com.zheng.zhengstartuppage.entity.user.UserDataEntity;
import com.zheng.zhengstartuppage.entity.user.UserEntity;
import com.zheng.zhengstartuppage.exception.IllegalResultClassException;
import com.zheng.zhengstartuppage.service.UserService;
import com.zheng.zhengstartuppage.util.returns.AppResult;
import com.zheng.zhengstartuppage.util.CookieUtil;
import com.zheng.zhengstartuppage.util.SessionUtil;
import com.zheng.zhengstartuppage.util.annotation.LoginMethod;
import com.zheng.zhengstartuppage.util.returns.ReturnsData;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * @author : 陈征
 * @date : 2021-09-30 21:03
 */

@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    @LoginMethod
    @RequestMapping("/login")
    public AppResult login(HttpServletResponse response,
                           @Valid UserEntity userEntity,
                           Errors errors) throws IllegalResultClassException, IllegalBlockSizeException, BadPaddingException {
        UserEntity sessionUser;
        if ((sessionUser = SessionUtil.getUser()) != null) {
            UserDataEntity userDataEntity = userService.getUserDataByUserId(sessionUser.getId());
            userService.updateUserLastLoginTime(sessionUser.getId());
            return AppResult.success()
                    .grab(sessionUser)
                    .grab(userDataEntity);
        }
        if (errors.hasErrors()) {
            return AppResult.fail("用户名或密码为空, 或含有特殊字符");
        }
        ReturnsData returnsData = userService.loginService(userEntity);

        if (returnsData.getData() != null)
            response.addCookie(CookieUtil.generateUserCookie());

        return AppResult.success()
                .grabAll(returnsData);
    }

    @LoginMethod
    @PostMapping("/register")
    public AppResult register(HttpServletResponse response,
                              @Valid UserEntity userEntity,
                              Errors errors) throws IllegalResultClassException, IllegalBlockSizeException, BadPaddingException {
        if (errors.hasErrors()) {
            return AppResult.fail("用户名或密码为空, 或含有特殊字符");
        }

        ReturnsData returnsData = userService.registerService(userEntity);
        if (returnsData.getData() != null)
            response.addCookie(CookieUtil.generateUserCookie());
        return AppResult.success()
                .grabAll(returnsData);
    }

    @PostMapping("/logout")
    public AppResult logout(HttpServletResponse response) {
        SessionUtil.getHttpSession().invalidate();
        response.addCookie(CookieUtil.removeCookie());
        return AppResult.success();
    }

}
