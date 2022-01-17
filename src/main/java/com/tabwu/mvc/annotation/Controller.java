package com.tabwu.mvc.annotation;

import java.lang.annotation.*;

/**
 * @PROJECT_NAME: wu-spring_mvc
 * @USER: tabwu
 * @DATE: 2022/1/17 14:08
 * @DESCRIPTION:
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface Controller {
    String value() default "";
}
