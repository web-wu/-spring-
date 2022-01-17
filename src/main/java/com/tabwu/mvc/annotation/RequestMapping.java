package com.tabwu.mvc.annotation;

import java.lang.annotation.*;

/**
 * @PROJECT_NAME: wu-spring_mvc
 * @USER: tabwu
 * @DATE: 2022/1/17 15:30
 * @DESCRIPTION:
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE,ElementType.METHOD})
@Documented
public @interface RequestMapping {
    String value() default "";
}
