package com.tabwu.mybatis.executor;

import com.tabwu.mybatis.pojo.Configuration;
import com.tabwu.mybatis.pojo.Mapper;

import java.util.List;

/**
 * @PROJECT_NAME: wu-mybatis
 * @USER: tabwu
 * @DATE: 2022/1/26 10:23
 * @DESCRIPTION:
 */
public interface Executor {

    public <E> List<E> query(Configuration configuration, Mapper mapper, Object ...param);

    public int update(Configuration configuration, Mapper mapper, Object ...param);

}
