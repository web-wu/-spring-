package com.tabwu.mybatis.pojo;

/**
 * @PROJECT_NAME: wu-mybatis
 * @USER: tabwu
 * @DATE: 2022/1/25 15:45
 * @DESCRIPTION:
 */
public class Mapper {
    private String id;
    private Class<?> resultType;
    private Class<?> paramterType;
    private String sql;

    public Mapper() {
    }

    public Mapper(String id, Class<?> resultType, Class<?> paramterType, String sql) {
        this.id = id;
        this.resultType = resultType;
        this.paramterType = paramterType;
        this.sql = sql;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Class<?> getResultType() {
        return resultType;
    }

    public void setResultType(Class<?> resultType) {
        this.resultType = resultType;
    }

    public Class<?> getParamterType() {
        return paramterType;
    }

    public void setParamterType(Class<?> paramterType) {
        this.paramterType = paramterType;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }
}
