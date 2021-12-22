package com.zheng.zhengstartuppage.util;

import com.zheng.zhengstartuppage.entity.user.UserEntity;

import javax.servlet.http.Cookie;

/**
 * @author : 陈征
 * @date : 2021-09-30 20:50
 */

public class CookieUtil {
    public static String splitMark = "#&$&#";

    public static Cookie generateUserCookie() {
        Cookie cookie = new Cookie("U", SessionUtil.getUser().getId() + splitMark + SessionUtil.getUserToken());
        cookie.setPath("/");
        cookie.setMaxAge(30 * 24 * 60 * 60);
        cookie.setHttpOnly(true);
        return cookie;
    }

    public static Cookie generateUserCookie(UserEntity userEntity) {
        Cookie cookie = new Cookie("U", userEntity.getId() + splitMark + SessionUtil.getUserToken());
        cookie.setPath("/");
        cookie.setMaxAge(30 * 24 * 60 * 60);
        cookie.setHttpOnly(true);
        return cookie;
    }

    public static Cookie verifyUserCookie(Cookie[] cookies) {
        int index;
        if ((index = hasUserCookie(cookies)) != -1) {
            return cookies[index];
        }
        return null;
    }

    public static int hasUserCookie(Cookie[] cookies) {
        if (cookies == null) {
            return -1;
        }
        for (int i = 0; i < cookies.length; i++) {
            if ("U".equals(cookies[i].getName())) {
                return i;
            }
        }
        return -1;
    }

    public static Cookie removeCookie() {
        Cookie cookie = new Cookie("U", "");
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setMaxAge(0);
        return cookie;
    }
}
