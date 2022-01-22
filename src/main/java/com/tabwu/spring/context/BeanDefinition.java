package com.tabwu.spring.context;

/**
 * @PROJECT_NAME: wu-spring
 * @USER: tabwu
 * @DATE: 2021/12/30 11:29
 * @DESCRIPTION:
 */
public class BeanDefinition {
    private Class clazz;
    private String scope;

    public Class getClazz() {
        return clazz;
    }

    public void setClazz(Class clazz) {
        this.clazz = clazz;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }
}
