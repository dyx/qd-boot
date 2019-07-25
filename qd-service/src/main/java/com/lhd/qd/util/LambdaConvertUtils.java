package com.lhd.qd.util;

import com.baomidou.mybatisplus.core.toolkit.LambdaUtils;
import com.baomidou.mybatisplus.core.toolkit.support.ColumnCache;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.core.toolkit.support.SerializedLambda;
import org.apache.ibatis.reflection.property.PropertyNamer;

/**
 * lambda表达式转换为字符串
 * @author lhd
 */
public class LambdaConvertUtils {

    public static <T> String lambdaToProperty(SFunction<T, ?> method) {

        if (method == null) {
            return "";
        }

        return PropertyNamer.methodToProperty(lambdaToString(method));
    }

    public static <T> String lambdaToColumn(SFunction<T, ?> method) {

        if (method == null) {
            return "";
        }

        SerializedLambda lambda = LambdaUtils.resolve(method);
        String propertyName = LambdaUtils.formatKey(PropertyNamer.methodToProperty(lambda.getImplMethodName()));
        ColumnCache columnCache = LambdaUtils.getColumnMap(lambda.getInstantiatedMethodType()).get(propertyName);

        return columnCache != null ? columnCache.getColumnSelect() : "";
    }

    private static <T> String lambdaToString(SFunction<T, ?> method) {

        if (method == null) {
            return "";
        }

        return LambdaUtils.resolve(method).getImplMethodName();
    }
}
