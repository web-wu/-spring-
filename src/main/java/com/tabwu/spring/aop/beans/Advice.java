package com.tabwu.spring.aop.beans;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @PROJECT_NAME: wu-spring
 * @USER: tabwu
 * @DATE: 2022/1/7 14:18
 * @DESCRIPTION:
 */
public class Advice {

    private Method adviceMethod;
    private Class<?> aspectClass;

    protected void invokeAspectMethod(Object proxy, Method method, Object[] args) {
        try {
            adviceMethod.invoke(aspectClass.getDeclaredConstructor().newInstance(), args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Advice() {
    }

    public Advice(Method adviceMethod, Class aspectClass) {
        this.adviceMethod = adviceMethod;
        this.aspectClass = aspectClass;
    }

    public Method getAdviceMethod() {
        return adviceMethod;
    }

    public void setAdviceMethod(Method adviceMethod) {
        this.adviceMethod = adviceMethod;
    }

    public Class<?> getAspectClass() {
        return aspectClass;
    }

    public void setAspectClass(Class<?> aspectClass) {
        this.aspectClass = aspectClass;
    }
}


