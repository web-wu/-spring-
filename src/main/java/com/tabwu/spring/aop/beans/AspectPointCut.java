package com.tabwu.spring.aop.beans;

import org.aspectj.weaver.tools.PointcutExpression;
import org.aspectj.weaver.tools.PointcutParser;

/**
 * @PROJECT_NAME: wu-spring
 * @USER: tabwu
 * @DATE: 2022/1/19 9:44
 * @DESCRIPTION:
 */
public class AspectPointCut implements PointCut{

    private String expression;

    public AspectPointCut(String expression) {
        this.expression = expression;
    }

    @Override
    public String getExpression() {
        return this.expression;
    }

    @Override
    public PointcutExpression buildPointCutExpression() {
        //**** 切点表达式与目标类中的方法匹配
        PointcutParser parser = PointcutParser
                .getPointcutParserSupportingAllPrimitivesAndUsingContextClassloaderForResolution();

        return parser.parsePointcutExpression(this.expression);
    }
}
