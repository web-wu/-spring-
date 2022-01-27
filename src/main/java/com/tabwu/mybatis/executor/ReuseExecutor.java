package com.tabwu.mybatis.executor;

import com.tabwu.mybatis.pojo.Mapper;

import java.sql.Connection;
import java.util.List;

/**
 * @PROJECT_NAME: wu-mybatis
 * @USER: tabwu
 * @DATE: 2022/1/26 10:25
 * @DESCRIPTION:
 */
public class ReuseExecutor extends BaseExecutor{
    @Override
    public <E> List<E> doQuery(Connection connection, Mapper mapper, Object... param) {
        return null;
    }

    @Override
    public int doUpdate(Connection connection, Mapper mapper, Object... param) {
        return 0;
    }
}
