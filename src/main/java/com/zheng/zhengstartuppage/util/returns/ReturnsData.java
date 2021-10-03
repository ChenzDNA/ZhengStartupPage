package com.zheng.zhengstartuppage.util.returns;

import com.zheng.zhengstartuppage.exception.IllegalResultClassException;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : 陈征
 * @date : 2021-10-03 20:15
 */

@Data
public class ReturnsData {
    private Map<String, Object> data;

    private ReturnsData(Object... objects) throws IllegalResultClassException {
        this.data = new HashMap<>();
        for (Object obj : objects) {
            this.data.put(AppResult.getObjectClassName(obj), obj);
        }
    }

    public static ReturnsData returns(Object... objects) throws IllegalResultClassException {
        return new ReturnsData(objects);
    }
}
