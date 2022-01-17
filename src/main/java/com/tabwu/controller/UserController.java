package com.tabwu.controller;

import com.tabwu.mvc.annotation.Controller;
import com.tabwu.mvc.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @PROJECT_NAME: wu-spring_mvc
 * @USER: tabwu
 * @DATE: 2022/1/7 16:09
 * @DESCRIPTION:
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/findAll")
    public String findAll() {
        return "find all users";
    }


    @RequestMapping("/findByUsername")
    public String findByUsername(HttpServletRequest req, HttpServletResponse resp, String username) {
        return "find user by username";
    }
}
