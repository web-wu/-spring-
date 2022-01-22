package com.tabwu.spring.context;

import com.tabwu.spring.annotation.Component;
import com.tabwu.spring.aop.beans.Advisor;

import java.util.List;

/**
 * @PROJECT_NAME: wu-spring
 * @USER: tabwu
 * @DATE: 2021/12/31 11:08
 * @DESCRIPTION:
 */
public abstract class AbstractAOPProxyCreator implements BeanPostProcessor {

    //构建所有Advisor, 这块可以使用缓存,只需要首次加载时构建即可
    protected abstract List<Advisor> buildAdvisors();

    //获取匹配的Advisor
    protected abstract List<Advisor> getMatchedAdvisor(Object bean);

    //创建代理对象
    protected abstract Object createProxy(List<Advisor> advisors, Object bean, String beanName);

    @Override
    public Object beanPostProcessorBeforeInitialization(Object bean, String beanName) {
        return bean;
    }

    @Override
    public Object beanPostProcessorAfterInitialization(Object bean, String beanName) {
        //构建所有Advisor
        List<Advisor> advisors = buildAdvisors();
        if (advisors == null) {
            return bean;
        }
        //根据bean对象中的方法去匹配是否满足切面中的expression表达式 ，获取匹配的Advisor
        advisors = this.getMatchedAdvisor(bean);
        if (advisors == null) {
            return bean;
        }
        //根据获取的advisor生成代理对象
        Object proxyObjext = createProxy(advisors, bean, beanName);

        return proxyObjext == null ? bean : proxyObjext;
    }
}
