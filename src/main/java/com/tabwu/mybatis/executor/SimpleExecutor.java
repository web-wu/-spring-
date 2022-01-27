package com.tabwu.mybatis.executor;

import com.tabwu.mybatis.handler.GenericTokenParser;
import com.tabwu.mybatis.handler.ParameterMappingTokenHandler;
import com.tabwu.mybatis.pojo.BoundSql;
import com.tabwu.mybatis.pojo.Mapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * @PROJECT_NAME: wu-mybatis
 * @USER: tabwu
 * @DATE: 2022/1/26 10:24
 * @DESCRIPTION:
 */
public class SimpleExecutor extends BaseExecutor{

    @Override
    public <E> List<E> doQuery(Connection connection, Mapper mapper, Object... param) {
        String sql = mapper.getSql();
        BoundSql boundSql = getBoundSql(sql);
        executeQuery(connection,boundSql.getSqlText());
        return null;
    }

    @Override
    public int doUpdate(Connection connection, Mapper mapper, Object... param) {
        return 0;
    }

    private void executeQuery(Connection connection, String sql) {
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    private BoundSql getBoundSql(String sql) {
        ParameterMappingTokenHandler parameterMappingTokenHandler = new ParameterMappingTokenHandler();
        GenericTokenParser genericTokenParser = new GenericTokenParser("#{", "}", parameterMappingTokenHandler);
        String sqlText = genericTokenParser.parse(sql);
        return new BoundSql(sqlText, parameterMappingTokenHandler.getParameterMappings());
    }
}
