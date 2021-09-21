package com.lhd.qd.trans.annotation;

import com.lhd.qd.trans.consts.RefTransType;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 参照翻译注解
 * @author lhd
 */
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RefTrans {
    RefTransType type();
    String readFieldName();
    String[] writeFieldNames();
}
