package com.zheng.zhengstartuppage.interceptor;

import com.zheng.zhengstartuppage.util.AppResult;
import com.zheng.zhengstartuppage.util.CookieUtil;
import com.zheng.zhengstartuppage.util.SessionUtil;
import com.zheng.zhengstartuppage.util.annotation.LoginMethod;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author : 陈征
 * @date : 2021-09-30 20:46
 */

public class UrlInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        SessionUtil.setHttpSession(request.getSession());
        final ServletOutputStream out = response.getOutputStream();
        if (!(handler instanceof HandlerMethod handlerMethod)) {
            return true;
        }
        response.setContentType("application/json;charset=utf-8");
        final Cookie[] cookies = request.getCookies();

        if (!CookieUtil.verifyCookie(cookies, handlerMethod)) {
            out.write(AppResult.fail("未登录").toString().getBytes());
            return false;
        }

        return true;
    }
}
