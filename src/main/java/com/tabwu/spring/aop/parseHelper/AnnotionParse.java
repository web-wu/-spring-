package com.tabwu.spring.aop.parseHelper;

import com.tabwu.spring.aop.beans.*;
import com.tabwu.spring.context.WuApplicationContext;
import org.aspectj.lang.annotation.*;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @PROJECT_NAME: wu-spring
 * @USER: tabwu
 * @DATE: 2022/1/19 10:22
 * @DESCRIPTION:
 */
public class AnnotionParse implements ConfigParse{
    /**
     * 避免重复构建，增加缓存
     * 以切面类的全限定类名为key
     * 缓存所有的切面类的advisor
     */
    private final Map<String, List<Advisor>> cache = new ConcurrentHashMap<>();

    @Override
    public List<Advisor> parse() {
        if(cache.size() != 0) {
            return getAdvisorsFromCache();
        }
        //获取所有的切面类字节码文件
        List<Class> aspectClassList = getAllAspectClasses();
        if (aspectClassList.size() <= 0) {
            return null;
        }
        for (Class clazz : aspectClassList) {
            //以切面类的全限定类名为key 存入缓存
            cache.putIfAbsent(clazz.getName(), getAdvisorsByAspect(clazz));
        }

        return getAdvisorsFromCache();
    }

    /**
     * 根据Aspect类生成Advisor类
     * @param class1
     * @return
     */
    private List<Advisor> getAdvisorsByAspect(Class class1) {
        List<Advisor> advisors = new ArrayList<>();
        //遍历切面类中所有方法
        for (Method method : getAdvisorMethods(class1)) {
            Advisor advisor = null;
            try {
                //根据切面类中的方法和 切面对象aspect 生成advisor对象
                advisor = getAdvisor(method, class1);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (advisor != null) {
                advisors.add(advisor);
            }
        }
        return advisors;
    }

    private List<Advisor> getAdvisorsFromCache() {
        ArrayList<Advisor> advisors = new ArrayList<>();
        for (Map.Entry<String, List<Advisor>> entry : cache.entrySet()) {
            for (Advisor advisor : entry.getValue()) {
                advisors.add(advisor);
            }
        }

        return advisors;
    }

    private List<Class> getAllAspectClasses() {
        List<Class> aspectClazzList = WuApplicationContext.aspectClazzList;
        return aspectClazzList;
    }

    //根据切面类获取所有的advice方法
    private List<Method> getAdvisorMethods(Class clazz) {
        Method[] Methods = clazz.getDeclaredMethods();
        ArrayList<Method> methodList = new ArrayList<>();
        for (Method method : Methods) {
            methodList.add(method);
        }
        return methodList;
    }

    //生成advisor对象
    private Advisor getAdvisor(Method method, Class aspectClass) {
        Advisor advisor = new Advisor();
        //将用户自定义切点 转换为 spring的 Aspect切点类型
        advisor.setAspectClass(aspectClass);
        //从切面类的方法method中获取切点对象，内含切面表达式 并设置
        advisor.setPointCut(getPointcut(method));
        //根据切面对象与切面对象中的方法构建增强对象 advice
        advisor.setAdvice(getAdvice(method, aspectClass));
        return advisor;
    }

    //获取 判断 增强类型
    private Advice getAdvice(Method method, Class aspectClass) {
        if(method.isAnnotationPresent(Before.class)) {
            return new BeforeAdvice(method, aspectClass);
        }
        if(method.isAnnotationPresent(AfterReturning.class)) {
            return new AfterReturningAdvice(method, aspectClass);
        }
        if(method.isAnnotationPresent(AfterThrowing.class)) {
            return new AfterThrowingAdvice(method, aspectClass);
        }

        if(method.isAnnotationPresent(After.class)) {
            return new AfterAdvice(method, aspectClass);
        }

        if(method.isAnnotationPresent(Around.class)) {
            return new AroundAdvice(method, aspectClass);
        }

        return null;
    }

    //根据切面中的方法构建切点对象，内含切面表达式
    private PointCut getPointcut(Method method) {
        AspectPointCut aspectPointCut = null;
        if (method.isAnnotationPresent(Before.class)) {
            Before beforeAnnotation = method.getDeclaredAnnotation(Before.class);
            aspectPointCut = new AspectPointCut(beforeAnnotation.value());
        }
        if (method.isAnnotationPresent(AfterReturning.class)) {
            AfterReturning fterReturningAnnotation = method.getDeclaredAnnotation(AfterReturning.class);
            aspectPointCut = new AspectPointCut(fterReturningAnnotation.value());
        }
        if (method.isAnnotationPresent(AfterThrowing.class)) {
            AfterThrowing throwingAnnotation = method.getDeclaredAnnotation(AfterThrowing.class);
            aspectPointCut = new AspectPointCut(throwingAnnotation.value());
        }
        if (method.isAnnotationPresent(After.class)) {
            After afterAnnotation = method.getDeclaredAnnotation(After.class);
            aspectPointCut = new AspectPointCut(afterAnnotation.value());
        }
        if (method.isAnnotationPresent(Around.class)) {
            Around aroundAnnotation = method.getDeclaredAnnotation(Around.class);
            aspectPointCut = new AspectPointCut(aroundAnnotation.value());
        }
        return aspectPointCut;
    }
}
