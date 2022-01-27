package com.tabwu.mybatis.handler;

import com.tabwu.mybatis.pojo.ParameterMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * @PROJECT_NAME: wu-mybatis
 * @USER: tabwu
 * @DATE: 2022/1/26 13:45
 * @DESCRIPTION:
 */
public class ParameterMappingTokenHandler implements TokenHandler{
    private List<ParameterMapping> parameterMappings = new ArrayList<>();

    //context 是参数名称 #{id} #{username}
    @Override
    public String handleToken(String content) {
        parameterMappings.add(buildParameterMapping(content));
        return "?";
    }

    private ParameterMapping buildParameterMapping(String content) {
        ParameterMapping parameterMapping = new ParameterMapping(content);
        return parameterMapping;
    }

    public List<ParameterMapping> getParameterMappings() {
        return parameterMappings;
    }

    public void setParameterMappings(List<ParameterMapping> parameterMappings) {
        this.parameterMappings = parameterMappings;
    }
}
