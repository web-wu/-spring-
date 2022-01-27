package com.tabwu.mybatis.handler;

/**
 * @PROJECT_NAME: wu-mybatis
 * @USER: tabwu
 * @DATE: 2022/1/26 13:38
 * @DESCRIPTION:
 */
public class GenericTokenParser{

    private String startToken;
    private String endToken;
    private TokenHandler handler;

    public GenericTokenParser(String startToken, String endToken, TokenHandler handler) {
        this.startToken = startToken;
        this.endToken = endToken;
        this.handler = handler;
    }

    //insert into user (username, password) values (#{username}, #{password})
    //需要将 #{username}, #{password} 替换为 ？， 将username, password 加入到parameterMappings中
    public String parse(String sql) {
        int startIndex = 0;
        int endIndex = 0;
        String replaceChar = null;
        while (sql.indexOf(this.startToken,startIndex) != -1 && sql.indexOf(this.endToken,endIndex) != -1) {
            // parameter = #{password}
            int startTagIndex = sql.indexOf(this.startToken);
            int endTagIndex = sql.indexOf(this.endToken);
            String parameter = sql.substring(startTagIndex, endTagIndex + 1);
            String paramName = parameter.substring(2, parameter.length() - 1);
            replaceChar = this.handler.handleToken(paramName);
            sql = sql.replace(parameter, replaceChar);
            startIndex = startTagIndex + 1;
            endIndex = endTagIndex + 1;
        }
        return sql;
    }
}
