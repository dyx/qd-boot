package com.lhd.qd.util;

import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.lhd.qd.constant.CommonConsts;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * json 工具
 *
 * @author lhd
 */
public class JacksonUtils {

    public final static Map<Class, JsonSerializer> SERIALIZER_MAP;
    static {
        SERIALIZER_MAP = new LinkedHashMap<>();
        SERIALIZER_MAP.put(Long.class, ToStringSerializer.instance);
        SERIALIZER_MAP.put(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(CommonConsts.DEFAULT_DATE_TIME_FORMAT)));
        SERIALIZER_MAP.put(LocalDate.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(CommonConsts.DEFAULT_DATE_TIME_FORMAT)));
    }

    private final static ObjectMapper OBJECT_MAPPER;
    static {
        OBJECT_MAPPER = new ObjectMapper();
        OBJECT_MAPPER.setDateFormat(new SimpleDateFormat(CommonConsts.DEFAULT_DATE_TIME_FORMAT));

        SimpleModule simpleModule = new SimpleModule();
        SERIALIZER_MAP.forEach(simpleModule::addSerializer);
        OBJECT_MAPPER.registerModule(simpleModule);
    }

    /**
     * 对象转换字符串
     * @param obj
     * @return
     */
    public static String toStr(Object obj) {
        if (obj == null) {
            return null;
        }
        String str = null;
        try {
            str = OBJECT_MAPPER.writeValueAsString(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }

    /**
     * 字符串转换对象
     * @param str
     * @return
     */
    public static Object toObj(String str) {
        return toObj(str, Object.class);
    }

    /**
     * 字符串转换指定类型
     * @param str
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T toObj(String str, Class<T> clazz) {

        if (StrUtil.isEmpty(str) || clazz == null) {
            return null;
        }

        T obj = null;
        try {
            obj = OBJECT_MAPPER.readValue(str, clazz);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }

}
