package com.tabwu.mybatis.utils;

import java.io.InputStream;

/**
 * @PROJECT_NAME: wu-mybatis
 * @USER: tabwu
 * @DATE: 2022/1/25 15:42
 * @DESCRIPTION:
 */
public class Resources {

    public static InputStream getResource(String path) {
        return Resources.class.getClassLoader().getResourceAsStream(path);
    }

}
