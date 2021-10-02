package com.zheng.zhengstartuppage.util;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : 陈征
 * @date : 2021-09-30 21:05
 */

@Data
public class AppResult {

    private final Map<String, Object> data;

    private String message;

    private int code;

    private AppResult setCode(int code) {
        this.code = code;
        return this;
    }

    private AppResult(String message) {
        this.message = message;
        this.data = new HashMap<>();
    }

    public AppResult grab(String name, Object data) {
        this.data.put(name, data);
        return this;
    }

    public AppResult grab(Object data) {
        this.data.put(getObjectClassName(data), data);
        return this;
    }

    private String getObjectClassName(Object obj) {
        String[] classPath = obj.getClass().toString().split("\\.");
        String className = classPath[classPath.length - 1];
        className = className.substring(0, 1).toLowerCase() + className.substring(1);
        return className.substring(0, className.length() - 6);
    }

    public static AppResult success() {
        return new AppResult("success").setCode(200);
    }

    public static AppResult fail(String message) {
        return new AppResult(message).setCode(500);
    }

    @Override
    public String toString() {
        return "{\"message\":\"" + message +
                "\",\"code\":" + code +
                "}";
    }
}