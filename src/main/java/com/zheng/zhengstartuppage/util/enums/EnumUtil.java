package com.zheng.zhengstartuppage.util.enums;

import java.lang.reflect.Field;

/**
 * @author : 陈征
 * @date : 2021-10-04 20:12
 */

public class EnumUtil {
    public static boolean verify(Class<? extends Enum> clazz, String s) {
        for (Field field : clazz.getFields()) {
            if (field.getName().equals(s)) {
                return true;
            }
        }
        return false;
    }
}
