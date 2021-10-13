package com.zheng.zhengstartuppage.util.returns;

import com.zheng.zhengstartuppage.entity.BaseEntity;
import com.zheng.zhengstartuppage.exception.IllegalResultClassException;
import lombok.Data;
import lombok.Getter;

import java.util.*;

/**
 * @author : 陈征
 * @date : 2021-09-30 21:05
 */

@Getter
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

    public <T extends BaseEntity> AppResult grab(T data) {
        this.data.put(getObjectClassName(data), data);
        return this;
    }

    public AppResult grabAll(ReturnsData returnsData) {
        if (!returnsData.isSuccess())
            return fail(returnsData.getMessage());
        for (Object obj : returnsData.getData()) {
            if (!(obj instanceof BaseEntity)){
                continue;
            }
            String className = getObjectClassName(obj);
            if (this.data.containsKey(className)) {
                Object o = this.data.get(className);
                if (o instanceof List) {
                    ((List) o).add(obj);
                } else {
                    this.data.put(className, new ArrayList(Collections.singletonList(o)));
                }
            } else {
                this.data.put(getObjectClassName(obj), obj);
            }
        }
        return this;
    }

    public String getObjectClassName(Object obj) {
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
