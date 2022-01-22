package com.tabwu.spring.aop.proxy;

import com.tabwu.spring.aop.beans.Advisor;
import com.tabwu.spring.aop.interceptor.AdviceMethodInterceptor;
import com.tabwu.spring.aop.interceptor.MethodInterceptorChain;
import com.tabwu.spring.aop.parseHelper.TargetMethodMatchedAdvisors;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @PROJECT_NAME: wu-spring
 * @USER: tabwu
 * @DATE: 2022/1/19 17:17
 * @DESCRIPTION:
 */
public class CGLibDynamicProxy extends AOPProxy{
    public CGLibDynamicProxy(List<Advisor> advisors, Object targetObject) {
        super(advisors, targetObject);
    }

    @Override
    protected Object getProxyObject() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(this.getTargetObject().getClass());
        enhancer.setCallback(new AOPInterceptor());

        return enhancer.create();
    }


    private class AOPInterceptor implements MethodInterceptor {



        @Override
        public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
            //责任链模式 调用
            /*AdviceMethodInterceptor[] interceptors = TargetMethodMatchedAdvisors
                    .getAdviceMethodInterceptor(CGLibDynamicProxy.this.getAdvisors(),method);
            Object result = new MethodInterceptorChain(interceptors).intercept(proxy, method, args);

            return result;*/


            System.out.println("before advice method");
            Object result = null;
            try {
                result = method.invoke(CGLibDynamicProxy.this.getTargetObject(), args);
            } catch (Exception e) {
                System.out.println("afterThrow advice method");
                e.printStackTrace();
            }
            System.out.println("afterReturn advice method");
            return result;
        }
    }
}
