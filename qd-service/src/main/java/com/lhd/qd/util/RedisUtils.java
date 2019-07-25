package com.lhd.qd.util;

import com.lhd.qd.constant.http.ErrorCodeEnum;
import com.lhd.qd.exception.BusinessException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * redis工具类
 *
 * @author lhd
 */
@Component
public class RedisUtils {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    @Autowired
    private ValueOperations<String, String> valueOperations;
    @Autowired
    private HashOperations<String, String, Object> hashOperations;
    @Autowired
    private ListOperations<String, Object> listOperations;
    @Autowired
    private SetOperations<String, Object> setOperations;
    @Autowired
    private ZSetOperations<String, Object> zsetOperations;

    /**
     * 设置过期时间
     *
     * @param key
     * @param expireSeconds
     */
    public void expire(String key, long expireSeconds) {
        redisTemplate.expire(key, expireSeconds, TimeUnit.SECONDS);
    }

    /**
     * 存储key和value，并设置过期时间
     *
     * @param key
     * @param value
     * @param expireSeconds
     */
    public void setValue(String key, Object value, long expireSeconds) {
        valueOperations.set(key, JacksonUtils.toStr(value), expireSeconds, TimeUnit.SECONDS);
    }

    /**
     * 获取value
     *
     * @param key
     */
    public String getValue(String key) {
        return valueOperations.get(key);
    }

    /**
     * 获取value
     * @param key
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T getValue(String key, Class<T> clazz) {
        return JacksonUtils.toObj(valueOperations.get(key), clazz);
    }

    /**
     * 删除value
     * @param key
     */
    public void delValue(String key) {
        redisTemplate.delete(key);
    }

    /**
     * 存储hash结构
     *
     * @param key
     * @param fieldName
     * @param fieldValue
     */
    public void setHash(String key, String fieldName, Object fieldValue) {
        if (StringUtils.isEmpty(key) || StringUtils.isEmpty(fieldName)) {
            throw new BusinessException(ErrorCodeEnum.REDIS_EMPTY_KEY);
        }
        hashOperations.put(key, fieldName, fieldValue);
    }

    /**
     * 获取hash结构
     * @param key
     * @return
     */
    public Map<String, Object> getHash(String key) {
        return hashOperations.entries(key);
    }

    /**
     * 获取hash结构字段值
     *
     * @param key
     * @param fieldName
     * @return
     */
    public Object getHash(String key, String fieldName) {
        return hashOperations.get(key, fieldName);
    }

    /**
     * 删除hash结构字段值
     *
     * @param key
     * @param fieldName
     * @return
     */
    public void delHash(String key, String fieldName) {
        hashOperations.delete(key, fieldName);
    }
}
