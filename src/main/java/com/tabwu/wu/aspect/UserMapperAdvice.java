package com.tabwu.wu.aspect;

import com.tabwu.spring.annotation.Component;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

/**
 * @PROJECT_NAME: wu-spring
 * @USER: tabwu
 * @DATE: 2022/1/21 15:15
 * @DESCRIPTION:
 */
@Component
@Aspect
public class UserMapperAdvice {

    /*@Before("execution(public * com.tabwu.wu.mapper.UserMapper.findUserById(..))")
    public void before() {
        System.out.println("对目标方法进行了前置增强!");
    }*/
}
