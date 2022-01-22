package com.tabwu.spring.aop.beans;

/**
 * @PROJECT_NAME: wu-spring
 * @USER: tabwu
 * @DATE: 2022/1/19 9:35
 * @DESCRIPTION:
 */
public class Advisor {
    private Advice advice;
    private PointCut pointCut;
    private Class<?> aspectClass;


    public Advice getAdvice() {
        return advice;
    }

    public void setAdvice(Advice advice) {
        this.advice = advice;
    }

    public PointCut getPointCut() {
        return pointCut;
    }

    public void setPointCut(PointCut pointCut) {
        this.pointCut = pointCut;
    }

    public Class<?> getAspectClass() {
        return aspectClass;
    }

    public void setAspectClass(Class<?> aspectClass) {
        this.aspectClass = aspectClass;
    }
}
