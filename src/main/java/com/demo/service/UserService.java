package com.demo.service;

import com.demo.domain.PageBean;
import com.demo.domain.User;

import java.util.List;

public interface UserService {

    List<User> getAll();

    boolean update(User user);

    boolean addUser(User user);

    List<User> getByCondition(String name);

    User getById(Integer id);

    boolean delete(Integer id);

    PageBean getByPage(Integer page, Integer size);

}
