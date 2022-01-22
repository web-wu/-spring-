package com.tabwu.wu;

import com.tabwu.spring.annotation.Component;

/**
 * @PROJECT_NAME: wu-spring
 * @USER: tabwu
 * @DATE: 2021/12/30 13:50
 * @DESCRIPTION:
 */
@Component("order")
public class Order /*implements BeanPostProcessor*/ {
    /*@Override
    public Object beanPostProcessorBeforeInitialization(Object bean, String beanName) {
        System.out.println("before");
        return bean;
    }

    @Override
    public Object beanPostProcessorAfterInitialization(Object bean, String beanName) {
        System.out.println("after");
        return bean;
    }*/
}
