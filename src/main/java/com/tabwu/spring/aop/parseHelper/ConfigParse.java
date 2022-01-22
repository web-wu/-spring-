package com.tabwu.spring.aop.parseHelper;

import com.tabwu.spring.aop.beans.Advisor;

import java.util.List;

/**
 * @PROJECT_NAME: wu-spring
 * @USER: tabwu
 * @DATE: 2022/1/19 10:18
 * @DESCRIPTION:
 */
public interface ConfigParse {
    public List<Advisor> parse();
}
