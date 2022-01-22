package com.tabwu.spring.context;

/**
 * @PROJECT_NAME: wu-spring
 * @USER: tabwu
 * @DATE: 2021/12/30 15:33
 * @DESCRIPTION:
 */
public interface BeanPostProcessor {
    Object beanPostProcessorBeforeInitialization(Object bean, String beanName);

    Object beanPostProcessorAfterInitialization(Object bean, String beanName);
}
