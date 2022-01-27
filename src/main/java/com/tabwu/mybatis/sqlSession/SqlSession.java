package com.tabwu.mybatis.sqlSession;

import java.util.List;

/**
 * @PROJECT_NAME: wu-mybatis
 * @USER: tabwu
 * @DATE: 2022/1/25 16:48
 * @DESCRIPTION:
 */
public interface SqlSession {

    public int insert(String statementId, Object ...param) throws Exception;

    public int delete(String statementId, Object ...param) throws Exception;

    public int update(String statementId, Object ...param) throws Exception;

    public <T> T selectOno(String statementId, Object ...param) throws Exception;

    public <E> List<E> selectList(String statementId, Object ...param) throws Exception;

    public void commit() throws Exception;

    public void rollback() throws Exception;

    public <T> T getMapper(Class<T> mapperClass);

}
