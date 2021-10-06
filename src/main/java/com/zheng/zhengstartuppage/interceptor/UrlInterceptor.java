package com.zheng.zhengstartuppage.interceptor;

import com.zheng.zhengstartuppage.entity.user.UserEntity;
import com.zheng.zhengstartuppage.model.UserModel;
import com.zheng.zhengstartuppage.util.annotation.LoginMethod;
import com.zheng.zhengstartuppage.util.returns.AppResult;
import com.zheng.zhengstartuppage.util.CookieUtil;
import com.zheng.zhengstartuppage.util.SessionUtil;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author : 陈征
 * @date : 2021-09-30 20:46
 */

public class UrlInterceptor implements HandlerInterceptor {

    @Resource
    private UserModel userModel;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        SessionUtil.setHttpSession(request.getSession());
        SessionUtil.setIP(SessionUtil.getIpAddr(request));

        final ServletOutputStream out = response.getOutputStream();
        if (!(handler instanceof HandlerMethod handlerMethod)) {
            return true;
        }
        response.setContentType("application/json;charset=utf-8");
        if (SessionUtil.isLogin()) {
            return true;
        }

        final Cookie[] cookies = request.getCookies();
        Cookie userCookie;
        if ((userCookie = CookieUtil.verifyUserCookie(cookies)) == null) {
            // 填写账号密码登录
            if (handlerMethod.hasMethodAnnotation(LoginMethod.class)) {
                return true;
            }
            out.write(AppResult.fail("未登录").toString().getBytes());
            return false;
        } else {
            // 带 cookie 登录
            String value = userCookie.getValue();
            long id = Long.parseLong(value.substring(0, value.indexOf(CookieUtil.splitMark)));
            UserEntity userEntity;
            if ((userEntity = userModel.getUserById(id)) == null) {
                out.write(AppResult.fail("cookie: 找不到用户").toString().getBytes());
                return false;
            }
            if (value.substring(value.indexOf(CookieUtil.splitMark) + CookieUtil.splitMark.length())
                    .equals(SessionUtil.getUserToken(userEntity))) {
                SessionUtil.setUser(userEntity);
                return true;
            }
            out.write(AppResult.fail("cookie 验证出错！").toString().getBytes());
            return false;
        }
    }
}
