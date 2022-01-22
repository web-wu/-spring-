package com.tabwu.spring.aop.proxy;

import com.tabwu.spring.annotation.Component;
import com.tabwu.spring.aop.beans.Advisor;
import com.tabwu.spring.aop.parseHelper.AnnotionParse;
import com.tabwu.spring.aop.parseHelper.ConfigParse;
import com.tabwu.spring.aop.parseHelper.TargetMethodMatchedAdvisors;
import com.tabwu.spring.context.AbstractAOPProxyCreator;

import java.util.List;

/**
 * @PROJECT_NAME: wu-spring
 * @USER: tabwu
 * @DATE: 2022/1/19 14:10
 * @DESCRIPTION:
 */

@Component
public class AutoGenerateAOPProxyCreator extends AbstractAOPProxyCreator {

    private ConfigParse configParse = new AnnotionParse();
    private AOPProxyFactory aopProxyFactory = new AOPProxyFactory();

    @Override
    protected List<Advisor> buildAdvisors() {
        //获取到所有切面类的advisor
        return configParse.parse();
    }

    @Override
    protected List<Advisor> getMatchedAdvisor(Object bean) {
        return TargetMethodMatchedAdvisors.getMatchedAdvisor(bean,this.buildAdvisors());
    }

    @Override
    protected Object createProxy(List<Advisor> advisors, Object bean, String beanName) {
        return aopProxyFactory.getProxyObject(advisors, bean);
    }
}
