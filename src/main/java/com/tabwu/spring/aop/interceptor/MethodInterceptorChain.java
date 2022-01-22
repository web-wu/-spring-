package com.tabwu.spring.aop.interceptor;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @PROJECT_NAME: wu-spring
 * @USER: tabwu
 * @DATE: 2022/1/22 9:21
 * @DESCRIPTION:
 */
public class MethodInterceptorChain {
    private AdviceMethodInterceptor[] adviceMethodInterceptors;
    private int index = 0;

    public MethodInterceptorChain(AdviceMethodInterceptor[] adviceMethodInterceptors) {
        this.adviceMethodInterceptors = adviceMethodInterceptors;
    }

    public Object intercept(Object proxy, Method method, Object[] args) {
        if (index == adviceMethodInterceptors.length) {
            try {
                return method.invoke(proxy, args);
            } catch (IllegalAccessException | InvocationTargetException  e) {
                e.printStackTrace();
            }
        } else {
            return adviceMethodInterceptors[index++].interceptor(proxy,method,args,this);
        }
        return null;
    }


}
