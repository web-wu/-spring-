package com.tabwu.spring.aop.beans;

import com.tabwu.spring.aop.interceptor.AdviceMethodInterceptor;
import com.tabwu.spring.aop.interceptor.MethodInterceptorChain;

import java.lang.reflect.Method;

/**
 * @PROJECT_NAME: wu-spring
 * @USER: tabwu
 * @DATE: 2022/1/19 16:05
 * @DESCRIPTION:
 */
public class BeforeAdvice extends Advice implements AdviceMethodInterceptor {

    public BeforeAdvice(Method adviceMethod, Class aspectClass) {
        super(adviceMethod, aspectClass);
    }

    public void before(Object proxy, Method method, Object[] args) {
        this.invokeAspectMethod(proxy, method, args);
    }

    @Override
    public Object interceptor(Object proxy, Method method, Object[] args, MethodInterceptorChain chain) {
        this.before(proxy,method,args);
        return chain.intercept(proxy,method,args);
    }
}
