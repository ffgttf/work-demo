package com.demo;

import com.demo.controller.Code;
import com.demo.controller.Result;
import com.demo.dao.EmpDao;
import com.demo.dao.UserDao;
import com.demo.domain.User;
import com.demo.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


@SpringBootTest
class WorkDemoApplicationTests {

    @Autowired
    private UserDao userDao;
    @Autowired
    private EmpDao empDao;
    @Autowired
    private UserService userService;
    @Test
    void getById() {
        User user = userDao.getById(1);
        System.out.println(user);
    } 
    @Test
    void getByCondition() {
        List<User> list = empDao.getByCondition("Mar", "", "mar");
        for (User user:list) {
            System.out.println("user = " + user);
        }
    }
    @Test
    void getById2() {
        User user = userService.getById(1);
        System.out.println("user = " + user);
    }

}
