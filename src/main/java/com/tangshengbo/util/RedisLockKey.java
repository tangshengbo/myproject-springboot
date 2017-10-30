package com.tangshengbo.util;

import java.lang.annotation.*;

/**
 * Created by TangShengBo on 2017-10-30.
 */
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface RedisLockKey {
    /**
     * key的拼接顺序规则
     */
    int order() default 0;
}