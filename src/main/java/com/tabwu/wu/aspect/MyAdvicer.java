package com.tabwu.wu.aspect;

import com.tabwu.spring.annotation.Component;

import org.aspectj.lang.annotation.*;


/**
 * @PROJECT_NAME: wu-spring
 * @USER: tabwu
 * @DATE: 2021/12/31 10:39
 * @DESCRIPTION:
 */
@Component("myAdvicer")
@Aspect
public class MyAdvicer {

    @Before("execution(public * com.tabwu.wu.service.UserService.*(..))")
    public void before() {
        System.out.println("对目标方法进行了前置增强!");
    }

    /*@AfterReturning("execution(public * com.tabwu.wu.service.UserService.findAll(..))")
    public void afterReturning() {
        System.out.println("对目标方法进行了后置增强!");
    }
*/
    /*@AfterThrowing("execution(public * com.tabwu.wu.service.UserService.findAll(..))")
    public void afterThrowing() {
        System.out.println("目标方法------发生了异常!");
    }*/
}
