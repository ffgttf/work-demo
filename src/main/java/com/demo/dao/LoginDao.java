package com.demo.dao;

import com.demo.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface LoginDao {
    @Select("select name from user where username = #{userName} and password = #{password}")
    User login(User user);
}
