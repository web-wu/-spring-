package com.tabwu.spring.aop.proxy;

import com.tabwu.spring.aop.beans.Advisor;

import java.util.List;

/**
 * @PROJECT_NAME: wu-spring
 * @USER: tabwu
 * @DATE: 2022/1/19 15:08
 * @DESCRIPTION:
 */
public class AOPProxyFactory {


    public Object getProxyObject(List<Advisor> advisors, Object bean) {

        if (hasInterface(bean)) {
            return new JDKDynamicProxy(advisors, bean).getProxyObject();
        } else {
            return new CGLibDynamicProxy(advisors, bean).getProxyObject();
        }
    }



    private boolean hasInterface(Object bean) {
        Class<?>[] interfaces = bean.getClass().getInterfaces();

        return interfaces.length > 0 ? true : false;
    }
}
