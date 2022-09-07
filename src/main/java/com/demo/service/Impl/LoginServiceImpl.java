package com.demo.service.Impl;

import com.demo.dao.LoginDao;
import com.demo.domain.User;
import com.demo.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginDao loginDao;

    @Override
    public boolean checkUser(User user) {
        User login = loginDao.login(user);
        if (login == null) {
            return false;
        } else {
            return true;
        }
    }
}
