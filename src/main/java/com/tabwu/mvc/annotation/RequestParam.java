package com.tabwu.mvc.annotation;

import java.lang.annotation.*;

/**
 * @PROJECT_NAME: wu-spring_mvc
 * @USER: tabwu
 * @DATE: 2022/1/17 16:57
 * @DESCRIPTION:
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
@Documented
public @interface RequestParam {
    String value() default "";
}
