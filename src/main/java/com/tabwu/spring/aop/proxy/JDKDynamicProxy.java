package com.tabwu.spring.aop.proxy;

import com.tabwu.spring.aop.beans.*;
import com.tabwu.spring.aop.interceptor.AdviceMethodInterceptor;
import com.tabwu.spring.aop.interceptor.MethodInterceptorChain;
import com.tabwu.spring.aop.parseHelper.TargetMethodMatchedAdvisors;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

/**
 * @PROJECT_NAME: wu-spring
 * @USER: tabwu
 * @DATE: 2022/1/19 15:25
 * @DESCRIPTION:
 */
public class JDKDynamicProxy extends AOPProxy implements InvocationHandler {

    public JDKDynamicProxy(List<Advisor> advisors, Object targetObject) {
        super(advisors, targetObject);
    }

    @Override
    protected Object getProxyObject() {
        return Proxy.newProxyInstance(this.getClass().getClassLoader(),this.getTargetObject().getClass().getInterfaces(),this::invoke);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        //责任链 调用实现
       /* AdviceMethodInterceptor[] interceptors = TargetMethodMatchedAdvisors
                                                .getAdviceMethodInterceptor(this.getAdvisors(),method);
        Object result = new MethodInterceptorChain(interceptors).intercept(proxy, method, args);

        return result;*/






        //简单实现
        //调用前置通知方法
        BeforeAdvice beforeAdvice = getBeforeAdvice(method);
        if (beforeAdvice != null) {
            beforeAdvice.before(proxy, method, args);
        }


        Object result = null;

        try {
            result = method.invoke(this.getTargetObject(), args);
        } catch (Exception e) {
            AfterThrowingAdvice afterThrowingAdvice = getAfterThrowMethods(method);
            if (afterThrowingAdvice != null) {
                afterThrowingAdvice.AfterThrowing(proxy, method, args);
            }

            e.printStackTrace();

            return null;
        }


        //调用后置通知方法
        AfterReturningAdvice afterReturningAdvice = getAfterReturningAdvice(method);
        if (afterReturningAdvice != null) {
            afterReturningAdvice.afterReturning(proxy, method, args);
        }

        return result;
    }

    private AfterThrowingAdvice getAfterThrowMethods(Method method) {
        for (Advisor advisor : this.getAdvisors()) {
            if (TargetMethodMatchedAdvisors.isMatche(advisor, method) && advisor.getAdvice() instanceof AfterThrowingAdvice) {
                AfterThrowingAdvice afterThrowingAdvice = (AfterThrowingAdvice) advisor.getAdvice();
                return afterThrowingAdvice;
            }
        }
        return null;
    }

    private AfterReturningAdvice getAfterReturningAdvice(Method method) {
        for (Advisor advisor : this.getAdvisors()) {
            if (TargetMethodMatchedAdvisors.isMatche(advisor, method) && advisor.getAdvice() instanceof AfterReturningAdvice) {
                AfterReturningAdvice afterReturningAdvice = (AfterReturningAdvice) advisor.getAdvice();
                return afterReturningAdvice;
            }
        }
        return null;
    }


    private BeforeAdvice getBeforeAdvice(Method method) {
        for (Advisor advisor : this.getAdvisors()) {
            if (TargetMethodMatchedAdvisors.isMatche(advisor,method) && advisor.getAdvice() instanceof BeforeAdvice) {
                BeforeAdvice beforeAdvice = (BeforeAdvice) advisor.getAdvice();
                return beforeAdvice;
            }
        }
        return null;
    }


}
