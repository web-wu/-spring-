package com.tabwu.spring.context;

/**
 * @PROJECT_NAME: wu-spring
 * @USER: tabwu
 * @DATE: 2021/12/30 14:41
 * @DESCRIPTION:
 */
public interface InitializationBean {

    void afterPropertiesset() throws Exception;
}
