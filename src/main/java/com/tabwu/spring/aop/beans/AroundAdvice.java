package com.tabwu.spring.aop.beans;

import java.lang.reflect.Method;

/**
 * @PROJECT_NAME: wu-spring
 * @USER: tabwu
 * @DATE: 2022/1/19 16:11
 * @DESCRIPTION:
 */
public class AroundAdvice extends Advice{
    public AroundAdvice(Method adviceMethod, Class aspectClass) {
        super(adviceMethod, aspectClass);
    }

    public void Around() {

    }
}
