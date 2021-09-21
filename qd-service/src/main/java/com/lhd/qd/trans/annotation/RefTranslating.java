package com.lhd.qd.trans.annotation;

import java.lang.annotation.*;

/**
 * 参照翻译分组注解
 * @author lhd
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RefTranslating {
    RefTrans[] value() default {};
}
