package com.tabwu.spring.aop.beans;

import java.lang.reflect.Method;

/**
 * @PROJECT_NAME: wu-spring
 * @USER: tabwu
 * @DATE: 2022/1/19 16:09
 * @DESCRIPTION:
 */
public class AfterAdvice extends Advice{
    public AfterAdvice(Method adviceMethod, Class aspectClass) {
        super(adviceMethod, aspectClass);
    }

    public void After() {

    }
}
