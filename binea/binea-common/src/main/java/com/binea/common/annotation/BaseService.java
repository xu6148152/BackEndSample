package com.binea.common.annotation;

import java.lang.annotation.*;

/**
 * 初始化继承BaseService的service
 * Created by binea on 2018/2/11.
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface BaseService {
}
