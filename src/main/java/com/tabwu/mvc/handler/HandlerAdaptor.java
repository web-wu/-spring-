package com.tabwu.mvc.handler;

import java.lang.reflect.Method;

/**
 * @PROJECT_NAME: wu-spring_mvc
 * @USER: tabwu
 * @DATE: 2022/1/17 16:05
 * @DESCRIPTION:
 */
public class HandlerAdaptor {
    private String url;
    private Object classObject;
    private Method method;

    public HandlerAdaptor(String url, Object classObject, Method method) {
        this.url = url;
        this.classObject = classObject;
        this.method = method;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Object getClassObject() {
        return classObject;
    }

    public void setClassObject(Object classObject) {
        this.classObject = classObject;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }
}
