package com.tabwu.spring.aop.proxy;

import com.tabwu.spring.aop.beans.Advisor;

import java.util.List;

/**
 * @PROJECT_NAME: wu-spring
 * @USER: tabwu
 * @DATE: 2022/1/19 15:22
 * @DESCRIPTION:
 */
public abstract class AOPProxy {
    private List<Advisor> advisors;

    private Object targetObject;

    public AOPProxy(List<Advisor> advisors, Object targetObject) {
        this.advisors = advisors;
        this.targetObject = targetObject;
    }

    protected abstract Object getProxyObject();

    public List<Advisor> getAdvisors() {
        return advisors;
    }

    public void setAdvisors(List<Advisor> advisors) {
        this.advisors = advisors;
    }

    public Object getTargetObject() {
        return targetObject;
    }

    public void setTargetObject(Object targetObject) {
        this.targetObject = targetObject;
    }
}
