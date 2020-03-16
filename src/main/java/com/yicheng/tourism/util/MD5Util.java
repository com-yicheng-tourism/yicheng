package com.yicheng.tourism.util;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

import java.util.UUID;


/**
 * 加密工具类
 */
public final class MD5Util {

    private MD5Util() { }
    //干扰数据 盐 防破解
    private static final String SALT = "mar%#$@";
    //散列算法类型为MD5
    private static final String ALGORITH_NAME = "md5";
    //hash的次数
    private static final int HASH_ITERATIONS = 10;

    public static String encrypt(String pswd) {

        return  new SimpleHash(ALGORITH_NAME, pswd, ByteSource.Util.bytes(SALT),HASH_ITERATIONS).toHex();
    }
}
