package com.tabwu;

import com.tabwu.mybatis.sqlSession.SqlSession;
import com.tabwu.mybatis.sqlSession.SqlSessionFactory;
import com.tabwu.mybatis.sqlSession.SqlSessionFactoryBuilder;
import com.tabwu.mybatis.utils.Resources;
import com.tabwu.wu.mapper.UserMapper;
import org.junit.jupiter.api.Test;


/**
 * @PROJECT_NAME: wu-mybatis
 * @USER: tabwu
 * @DATE: 2022/1/26 9:38
 * @DESCRIPTION:
 */
public class Test1 {


    @Test
    public void test1() throws Exception {
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().builder(Resources.getResource("SqlConfig.xml"));
        SqlSession sqlSession = sqlSessionFactory.openSqlSession();
        sqlSession.selectOno("com.tabwu.wu.mapper.UserMapper.selectById",1);
    }

    @Test
    public void test2() throws Exception {
        String sql = "insert into user (username, password) values (#{username}, #{password})";

        sql.replace("user", "?");

        System.out.println(sql);
    }

}
