package com.tabwu.spring.aop.parseHelper;

import com.tabwu.spring.aop.beans.Advisor;
import com.tabwu.spring.aop.beans.AfterReturningAdvice;
import com.tabwu.spring.aop.beans.BeforeAdvice;
import com.tabwu.spring.aop.interceptor.AdviceMethodInterceptor;
import org.aspectj.weaver.tools.ShadowMatch;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @PROJECT_NAME: wu-spring
 * @USER: tabwu
 * @DATE: 2022/1/19 14:36
 * @DESCRIPTION:
 */
public class TargetMethodMatchedAdvisors {


    public static boolean isMatche(Advisor advisor, Method method) {
        return advisor.getPointCut().buildPointCutExpression().matchesMethodExecution(method).alwaysMatches();
    }


    public static List<Advisor> getMatchedAdvisor(Object bean, List<Advisor> advisorList) {

        ArrayList<Advisor> advisors = new ArrayList<>();
        //遍历目标类中方法，根据bean方法匹配 切面类aspect、通知advice、切点类的 组合类 advisor
        for (Method method : bean.getClass().getDeclaredMethods()) {
            for (Advisor advisor : advisorList) {
                ShadowMatch match = advisor.getPointCut()
                                    .buildPointCutExpression()
                                    .matchesMethodExecution(method);
                if (match.alwaysMatches()) {
                    advisors.add(advisor);
                }
            }
        }

        if (advisors.size() <= 0) {
            return null;
        }

        return advisors;
    }

    public static AdviceMethodInterceptor[] getAdviceMethodInterceptor(List<Advisor> advisors, Method method) {
        ArrayList<AdviceMethodInterceptor> list = new ArrayList<>();
        for (Advisor advisor : advisors) {
            if (isMatche(advisor,method)) {
                if (advisor.getAdvice() instanceof BeforeAdvice) {
                    list.add((BeforeAdvice) advisor.getAdvice());
                }
//                if (advisor.getAdvice() instanceof AfterReturningAdvice) {
//                    list.add((AfterReturningAdvice) advisor.getAdvice())
//                }
            }
        }
        return (AdviceMethodInterceptor[]) list.toArray();   //此处类型转换异常
    }
}
