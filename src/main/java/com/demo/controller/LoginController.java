package com.demo.controller;

import com.demo.domain.User;
import com.demo.service.LoginService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @ApiOperation("登录查询")
    @PostMapping
    public Result login(@RequestBody User user) {
        boolean flag = loginService.checkUser(user);
        return new Result(flag ? Code.CHECK_OK:Code.CHECK_ERR, flag);
    }
}
