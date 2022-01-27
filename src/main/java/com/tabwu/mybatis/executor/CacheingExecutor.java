package com.tabwu.mybatis.executor;

import com.tabwu.mybatis.pojo.Configuration;
import com.tabwu.mybatis.pojo.Mapper;

import java.util.List;

/**
 * @PROJECT_NAME: wu-mybatis
 * @USER: tabwu
 * @DATE: 2022/1/26 10:24
 * @DESCRIPTION:
 */
public class CacheingExecutor implements Executor{
    @Override
    public <E> List<E> query(Configuration configuration, Mapper mapper, Object... param) {
        return null;
    }

    @Override
    public int update(Configuration configuration, Mapper mapper, Object... param) {
        return 0;
    }
}
