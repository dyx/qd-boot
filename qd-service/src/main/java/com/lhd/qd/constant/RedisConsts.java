package com.lhd.qd.constant;

/**
 * redis常量
 * @author lhd
 */
public class RedisConsts {

    public final static String DICT_KEY = "DICT";

    private final static String USER_PREFIX = "USER:";
    private final static String USER_TOKEN_PREFIX = "USER:TOKEN:";

    public static String getUserKey(Long userId) {
        return USER_PREFIX + userId;
    }

    public static String getUserTokenKey(String clientId, Long userId) {
        return USER_TOKEN_PREFIX + userId + ":" + clientId;
    }
}
