package com.tabwu.wu.mapper;


import com.tabwu.wu.entity.User;

import java.util.List;

/**
 * @PROJECT_NAME: wu-mybatis
 * @USER: tabwu
 * @DATE: 2022/1/25 14:37
 * @DESCRIPTION:
 */
public interface UserMapper {

    void add(User user);

    void delete(Integer id);

    void update(User user);

    User selectById(Integer id);

    User findByUsername(String username);

    List<User> selectList();

}
