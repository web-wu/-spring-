package com.tabwu.mybatis.sqlSession;

import com.tabwu.mybatis.executor.Executor;
import com.tabwu.mybatis.executor.SimpleExecutor;
import com.tabwu.mybatis.pojo.Configuration;
import com.tabwu.mybatis.pojo.Mapper;

import java.util.List;

/**
 * @PROJECT_NAME: wu-mybatis
 * @USER: tabwu
 * @DATE: 2022/1/25 16:50
 * @DESCRIPTION:
 */
public class DefaultSqlSession implements SqlSession {
    private Configuration configuration;

    //暂时忽略缓存，直接使用简单的执行器
    private Executor executor = new SimpleExecutor();

    public DefaultSqlSession(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public int insert(String statementId, Object... param) throws Exception {
        Mapper mapper = configuration.getMapperMap().get(statementId);

        return executor.update(configuration,mapper,param);
    }

    @Override
    public int delete(String statementId, Object... param) throws Exception {
        Mapper mapper = configuration.getMapperMap().get(statementId);

        return executor.update(configuration,mapper,param);
    }

    @Override
    public int update(String statementId, Object... param) throws Exception {
        Mapper mapper = configuration.getMapperMap().get(statementId);

        return executor.update(configuration,mapper,param);
    }

    @Override
    public <T> T selectOno(String statementId, Object... param) throws Exception {
        Mapper mapper = configuration.getMapperMap().get(statementId);
        List<Object> list = executor.query(configuration, mapper, param);
        if (list.size() == 1) {
            return (T) list.get(0);
        } else {
            throw new RuntimeException("返回结果超过1个");
        }
    }

    @Override
    public <E> List<E> selectList(String statementId, Object... param) throws Exception {
        Mapper mapper = configuration.getMapperMap().get(statementId);
        return executor.query(configuration, mapper, param);
    }

    @Override
    public void commit() throws Exception {

    }

    @Override
    public void rollback() throws Exception {

    }

    @Override
    public <T> T getMapper(Class<T> mapperClass) {
        return null;
    }
}
