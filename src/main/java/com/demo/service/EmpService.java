package com.demo.service;

import com.demo.domain.PageBean;
import com.demo.domain.User;

import java.util.List;

public interface EmpService {

    boolean update(User user);

    boolean addUser(User user);

    List<User> getByCondition(String name,String uname,String userName);

    User getById(Integer id);

    boolean delete(Integer id);

    PageBean getByPage(Integer page, Integer size);
}
