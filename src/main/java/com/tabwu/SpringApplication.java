package com.tabwu;

import com.tabwu.spring.context.WuApplicationContext;
import com.tabwu.wu.ConfigClass;
import com.tabwu.wu.mapper.UserMapper;
import com.tabwu.wu.service.UserService;

/**
 * @PROJECT_NAME: wu-spring
 * @USER: tabwu
 * @DATE: 2021/12/29 15:49
 * @DESCRIPTION:
 */
public class SpringApplication {
    public static void main(String[] args) {
        WuApplicationContext applicationContext = new WuApplicationContext(ConfigClass.class);

        UserService userService = (UserService) applicationContext.getBean("userService");
        userService.findAll();
        userService.findById();

        UserMapper userMapper = (UserMapper) applicationContext.getBean("UserMapper");
        userMapper.findUserById();

    }
}
