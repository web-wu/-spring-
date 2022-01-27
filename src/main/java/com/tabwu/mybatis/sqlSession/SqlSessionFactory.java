package com.tabwu.mybatis.sqlSession;

/**
 * @PROJECT_NAME: wu-mybatis
 * @USER: tabwu
 * @DATE: 2022/1/25 16:47
 * @DESCRIPTION:
 */
public interface SqlSessionFactory {

    SqlSession openSqlSession();

}
