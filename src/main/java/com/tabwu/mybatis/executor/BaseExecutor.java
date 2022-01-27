package com.tabwu.mybatis.executor;

import com.tabwu.mybatis.pojo.Configuration;
import com.tabwu.mybatis.pojo.Mapper;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @PROJECT_NAME: wu-mybatis
 * @USER: tabwu
 * @DATE: 2022/1/26 10:23
 * @DESCRIPTION:
 */
public abstract class BaseExecutor implements Executor{
    @Override
    public <E> List<E> query(Configuration configuration, Mapper mapper, Object... param) {
        Connection connection = null;
        try {
            connection = configuration.getDataSource().getConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return this.doQuery(connection,mapper,param);
    }

    @Override
    public int update(Configuration configuration, Mapper mapper, Object... param) {
        Connection connection = null;
        try {
            connection = configuration.getDataSource().getConnection();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return this.doUpdate(connection,mapper,param);
    }

    public abstract  <E> List<E> doQuery(Connection connection, Mapper mapper, Object... param);

    public abstract int doUpdate(Connection connection, Mapper mapper, Object... param);
}
