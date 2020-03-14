package com.yicheng.tourism.util;

import java.util.UUID;

/**
 * 主键无法确定的,用此工具类自动生成32位定长的字符串
 */
public final class UUIDUtil {

    private UUIDUtil() { }

    public static String get() {
        return  UUID.randomUUID().toString().replaceAll("-","");
    }
}
