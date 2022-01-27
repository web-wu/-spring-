package com.tabwu.mybatis.pojo;

import javax.sql.DataSource;
import java.util.HashMap;

/**
 * @PROJECT_NAME: wu-mybatis
 * @USER: tabwu
 * @DATE: 2022/1/25 15:49
 * @DESCRIPTION:
 */
public class Configuration {

    private DataSource dataSource;

    private HashMap<String, Mapper> mapperMap = new HashMap<>();

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public HashMap<String, Mapper> getMapperMap() {
        return mapperMap;
    }

}
