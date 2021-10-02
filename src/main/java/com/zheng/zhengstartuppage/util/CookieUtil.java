package com.zheng.zhengstartuppage.util;

import com.zheng.zhengstartuppage.util.annotation.LoginMethod;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.http.Cookie;

/**
 * @author : 陈征
 * @date : 2021-09-30 20:50
 */

public class CookieUtil {
    public static Cookie generateUserCookie() {
        Cookie cookie = new Cookie("U", SessionUtil.getUserToken());
        cookie.setPath("/");
        cookie.setMaxAge(30 * 24 * 60 * 60);
        cookie.setHttpOnly(true);
        return cookie;
    }

    public static boolean verifyCookie(Cookie[] cookies, HandlerMethod handlerMethod) {
        if (!hasUserCookie(cookies)) {
            return handlerMethod.hasMethodAnnotation(LoginMethod.class);
        }
        return true;
    }

    public static boolean hasUserCookie(Cookie[] cookies) {
        if (cookies == null) {
            return false;
        }
        for (Cookie cookie : cookies) {
            if ("U".equals(cookie.getName())) {
                return cookie.getName().equals(SessionUtil.getUserToken());
            }
        }
        return false;
    }
}
