package com.ang.frontui.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MyRedisCache {

    String value() default "";

    /**
     * 增加的唯一key
     * @return
     */
    String key() default "";

    /**
     * 返回值类型是否list
     * @return
     */
    boolean isArray() default false;

    /**
     *  具体没个的反序列化类型
     * @return
     */
    Class returnType() default Object.class;

    /**
     * 是否是用户相关
     * @return
     */
    boolean isUserConn() default false;
}
