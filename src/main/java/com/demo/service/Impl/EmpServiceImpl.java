package com.demo.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.dao.EmpDao;
import com.demo.domain.PageBean;
import com.demo.domain.User;
import com.demo.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpDao empDao;

    @Override
    public boolean update(User user) {
        return empDao.update(user) > 0;
    }

    @Override
    public boolean addUser(User user) {
        return empDao.insert(user) > 0;
    }

    @Override
    public List<User> getByCondition(String name, String uname, String userName) {
        if (name != null && name.length() > 0) {
            name = ("%" + name + "%");
            uname = ("%" + uname + "%");
            userName = ("%" + userName + "%");
        }
//        QueryWrapper<User> wrapper = new QueryWrapper<>();
//        //设置查询条件
//        wrapper.like(StringUtils.hasText(name),"name", name);
//        wrapper.like(StringUtils.hasText(uname),"uname", uname);
//        wrapper.like(StringUtils.hasText(userName),"username", userName);
//        List<User> users = empDao.selectList(wrapper);
        List<User> users = empDao.getByCondition(name, uname, userName);
        return users;
    }

    @Override
    public User getById(Integer id) {
        User user = empDao.getById(id);
        return user;
    }

    @Override
    public boolean delete(Integer id) {
        return empDao.deleteById(id) > 0;
    }

    @Override
    public PageBean getByPage(Integer page, Integer size) {
        Page<User> empPage = new Page<>(page, size);
        Page<User> resultPage = this.empDao.selectPage(empPage, null);

        PageBean pageBean = new PageBean();
        pageBean.setData(resultPage.getRecords());
        pageBean.setTotal(resultPage.getTotal());
        return pageBean;
    }
}
