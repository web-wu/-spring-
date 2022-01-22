package com.tabwu.wu.service;

import com.tabwu.spring.annotation.Component;
import com.tabwu.wu.mapper.UserMapper;

/**
 * @PROJECT_NAME: wu-spring
 * @USER: tabwu
 * @DATE: 2021/12/31 10:56
 * @DESCRIPTION:
 */
@Component("UserMapper")
public class UserMapperImpl implements UserMapper {

    @Override
    public void findUserById() {
        System.out.println("findUserById");
    }
}
