package com.tabwu.spring.aop.interceptor;

import java.lang.reflect.Method;

/**
 * @PROJECT_NAME: wu-spring
 * @USER: tabwu
 * @DATE: 2022/1/22 9:13
 * @DESCRIPTION:
 */
public interface AdviceMethodInterceptor {

    public Object interceptor(Object proxy, Method method, Object[] args, MethodInterceptorChain chain);
}
