package com.tabwu.spring.aop.beans;

import java.lang.reflect.Method;

/**
 * @PROJECT_NAME: wu-spring
 * @USER: tabwu
 * @DATE: 2022/1/19 16:06
 * @DESCRIPTION:
 */
public class AfterReturningAdvice extends Advice{
    public AfterReturningAdvice(Method adviceMethod, Class aspectClass) {
        super(adviceMethod, aspectClass);
    }

    public void afterReturning(Object proxy, Method method, Object[] args) {
        this.invokeAspectMethod(proxy,method,args);
    }
}
