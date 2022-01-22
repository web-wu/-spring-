package com.tabwu.spring.aop.beans;

import org.aspectj.weaver.tools.PointcutExpression;

/**
 * @PROJECT_NAME: wu-spring
 * @USER: tabwu
 * @DATE: 2022/1/7 14:20
 * @DESCRIPTION:
 */
public interface PointCut {

    public String getExpression();

    public PointcutExpression buildPointCutExpression();
}
