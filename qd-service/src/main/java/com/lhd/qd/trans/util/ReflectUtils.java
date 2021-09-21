package com.lhd.qd.trans.util;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;

/**
 * @author lhd
 */
@Slf4j
public class ReflectUtils {

    public static Object getFieldValue(Object obj, String fieldName) {

        if (obj == null || fieldName == null || fieldName.length() == 0) {
            return null;
        }

        try {
            Field field = getField(obj.getClass(), fieldName);
            if (field != null) {
                field.setAccessible(true);
                return field.get(obj);
            }
        }
        catch (Exception e) {
            log.debug("反射获取字段值异常，class={}, fieldName={}, exception={}, cause={}", obj.getClass(), fieldName, e.getClass(), e.getClass());
        }

        return null;
    }

    public static void setFieldValue(Object obj, String fieldName, Object value) {

        if (obj == null || fieldName == null || fieldName.length() == 0) {
            return;
        }

        try {
            Field field = getField(obj.getClass(), fieldName);
            if (field != null) {
                field.setAccessible(true);
                field.set(obj, value);
            }
        }
        catch (Exception e) {
            log.debug("反射设置字段值异常，class={}, fieldName={}, value={}, exception={}, cause={}", obj.getClass(), fieldName, value, e.getClass(), e.getClass());
        }
    }

    public static Field getField(Class<?> beanClass, String fieldName) {

        for(; beanClass != null && beanClass != Object.class; beanClass = beanClass.getSuperclass()) {
            try {
                return beanClass.getDeclaredField(fieldName);
            }
            catch (NoSuchFieldException ignored) {}
        }
        return null;
    }
}
