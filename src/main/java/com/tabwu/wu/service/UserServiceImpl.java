package com.tabwu.wu.service;

import com.tabwu.spring.annotation.Autowired;
import com.tabwu.spring.annotation.Component;
import com.tabwu.wu.Order;

/**
 * @PROJECT_NAME: wu-spring
 * @USER: tabwu
 * @DATE: 2021/12/29 16:25
 * @DESCRIPTION:
 */
@Component("userService")
//@Scope("prototype")
//@Component
public class UserServiceImpl implements UserService {
    @Autowired
    private Order order;

    public Order getOrder() {
        return order;
    }

    @Override
    public void findAll() {
        System.out.println("target execute findAll method");
//        int i = 10 / 0;
    }

    @Override
    public void findById() {
        System.out.println("find By Id method execute");
    }

}
