package com.zheng.zhengstartuppage.util;


import com.zheng.zhengstartuppage.entity.user.UserEntity;
import lombok.Getter;
import lombok.Setter;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.http.HttpSession;

/**
 * @author : 陈征
 * @date : 2021-09-30 20:50
 */

public class SessionUtil {
    @Getter
    @Setter
    private static HttpSession httpSession;

    public static void setUser(UserEntity user) {
        httpSession.setAttribute("SessionToken", hashToken(user.getAccount() + user.getPassword()));
        httpSession.setAttribute("User", user);
    }

    private static String hashToken(String data) {
        return BCrypt.hashpw(data, BCrypt.gensalt());
    }

    public static UserEntity getUser() {
        return (UserEntity) httpSession.getAttribute("User");
    }

    public static String getUserToken() {
        return (String) httpSession.getAttribute("SessionToken");
    }

    public static boolean isLogin() {
        if (httpSession == null) {
            return false;
        }
        return httpSession.getAttribute("User") != null;
    }
}
