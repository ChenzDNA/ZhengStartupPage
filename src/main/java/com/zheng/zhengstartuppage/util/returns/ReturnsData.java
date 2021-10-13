package com.zheng.zhengstartuppage.util.returns;

import com.zheng.zhengstartuppage.exception.IllegalResultClassException;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author : 陈征
 * @date : 2021-10-03 20:15
 * 只供 service 层使用。
 */

@Getter
public class ReturnsData {
    private List<Object> data;

    private String message;

    private final boolean success;

    private ReturnsData(boolean success, Object... objects) {
        this.data = new ArrayList<>();
        this.success = success;
        this.data.addAll(Arrays.asList(objects));
    }

    private ReturnsData(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public static ReturnsData returns(Object... objects) {
        return new ReturnsData(true, objects);
    }

    public static ReturnsData returns(String message) {
        return new ReturnsData(false, message);
    }
}
