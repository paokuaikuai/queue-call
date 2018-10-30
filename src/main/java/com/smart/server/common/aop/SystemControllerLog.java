package com.smart.server.common.aop;

import java.lang.annotation.*;

/**
 * 自定义注解 拦截Controller
 * 
 * @author WS
 *
 */
@Target({ ElementType.PARAMETER, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented  
public @interface SystemControllerLog {
    /**
     * 描述业务操作 例:Xxx管理-执行Xxx操作
     * @return
     */
    String description() default "";
}


