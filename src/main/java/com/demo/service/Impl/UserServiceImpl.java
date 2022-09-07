package com.demo.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.dao.UserDao;
import com.demo.domain.PageBean;
import com.demo.domain.User;
import com.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;


    @Override
    public List<User> getAll() {
        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<>();
        lqw.select(User::getId, User::getName, User::getCreateDate);
        List<User> userList = userDao.selectList(lqw);
        return userList;
    }
    @Override
    public boolean update(User user) {
        return userDao.update(user) > 0;
    }

    @Override
    public boolean addUser(User user) {
        return userDao.insert(user) > 0;
    }

    @Override
    public List<User> getByCondition(String name) {
        if(name != null && name.length() > 0){
            name = ("%"+ name +"%");
        }
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        //设置查询条件
        wrapper.like("name", name);
        List<User> users = this.userDao.selectList(wrapper);
        return users;
    }

    @Override
    public User getById(Integer id) {
//        User user = userDao.selectById(id);
        User user = userDao.getById(id);
        return user;
    }

    @Override
    public boolean delete(Integer id) {
        return userDao.deleteById(id) > 0;
    }

    @Override
    public PageBean getByPage(Integer page, Integer size) {
        Page<User> userPage = new Page<>(page, size);
        Page<User> resultPage = this.userDao.selectPage(userPage, null);

        PageBean pageBean = new PageBean();
        pageBean.setData(resultPage.getRecords());
        pageBean.setTotal(resultPage.getTotal());
        return pageBean;
    }


}
