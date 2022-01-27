package com.tabwu.mybatis.sqlSession;

import com.tabwu.mybatis.config.XmlConfigBuilder;
import com.tabwu.mybatis.pojo.Configuration;

import java.io.InputStream;

/**
 * @PROJECT_NAME: wu-mybatis
 * @USER: tabwu
 * @DATE: 2022/1/25 16:58
 * @DESCRIPTION:
 */
public class SqlSessionFactoryBuilder {

    private Configuration configuration = new Configuration();

    public SqlSessionFactoryBuilder() {
    }

    public SqlSessionFactory builder(InputStream in) {
        configuration = new XmlConfigBuilder(this.configuration).loadXmlConfig(in);

        SqlSessionFactory sqlSessionFactory = new DefaultSqlSessionFactory(configuration);
        return sqlSessionFactory;
    }

}
