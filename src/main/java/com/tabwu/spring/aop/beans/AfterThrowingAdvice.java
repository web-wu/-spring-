package com.tabwu.spring.aop.beans;

import java.lang.reflect.Method;

/**
 * @PROJECT_NAME: wu-spring
 * @USER: tabwu
 * @DATE: 2022/1/19 16:08
 * @DESCRIPTION:
 */
public class AfterThrowingAdvice extends Advice {
    public AfterThrowingAdvice(Method adviceMethod, Class aspectClass) {
        super(adviceMethod, aspectClass);
    }

    public void AfterThrowing(Object proxy, Method method, Object[] args) {
        this.invokeAspectMethod(proxy, method, args);
    }
}
