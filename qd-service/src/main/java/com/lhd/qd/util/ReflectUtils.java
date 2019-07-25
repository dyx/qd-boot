package com.lhd.qd.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.math.BigDecimal;

/**
 * 反射工具
 * @author lhd
 */
@Slf4j
public class ReflectUtils {

    /**
     * 获得字段
     */
    public static Field getField(Object obj, String fieldName) {

        if (obj == null || StringUtils.isEmpty(fieldName)) {
            return null;
        }

        for (Class<?> clazz = obj.getClass(); clazz != Object.class; clazz = clazz.getSuperclass()) {
            try {
                return clazz.getDeclaredField(fieldName);
            } catch (Exception e) { }
        }

        log.debug("反射获取字段失败，字段：{}", fieldName);

        return null;
    }

    /**
     * 获得字段值
     */
    public static Object getFieldValue(Object obj, String fieldName) {
        return getFieldValue(obj, getField(obj, fieldName));
    }

    /**
     * 获得字段值
     */
    public static Object getFieldValue(Object obj, Field field) {

        Object value = null;
        try {
            if (field != null) {
                if (field.isAccessible()) {
                    value = field.get(obj);
                } else {
                    field.setAccessible(true);
                    value = field.get(obj);
                    field.setAccessible(false);
                }
            }
        } catch (Exception e) {
            log.debug("反射获取字段值失败，原因：{}" + e.getMessage());
        }

        return value;
    }

    /**
     * 根据字段名设置值，可根据类型赋值
     * @param obj
     * @param fieldName
     * @param value
     * @param type
     */
    public static void setFieldValue(Object obj, String fieldName, Object value, Class type) {
        setFieldValue(obj, getField(obj, fieldName), value, type);
    }

    /**
     * 根据字段设置值，可根据类型赋值
     * @param obj
     * @param field
     * @param value
     * @param type
     */
    public static void setFieldValue(Object obj, Field field, Object value, Class type) {
        try {

            if (value != null) {

                field.setAccessible(true);

                if (type != null) {

                    String valueStr = String.valueOf(value);

                    if (String.class.equals(type)) {
                        field.set(obj, valueStr);
                    }
                    else if (Integer.class.equals(type)) {

                        field.set(obj, Integer.valueOf(valueStr));
                    } else if (Long.class.equals(type)) {

                        field.set(obj, Long.valueOf(valueStr));
                    } else if (BigDecimal.class.equals(type)) {

                        field.set(obj, new BigDecimal(valueStr));
                    }
                    else {
                        field.set(obj, value);
                    }
                }
                else {
                    field.set(obj, value);
                }

                field.setAccessible(false);
            }
        } catch (Exception e) {

            log.debug("反射设置字段值失败，原因：{}", e.getMessage());
        }
    }
}
