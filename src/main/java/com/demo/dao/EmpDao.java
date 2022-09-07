package com.demo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demo.domain.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface EmpDao extends BaseMapper<User> {

//    @Select("select * from user where name like #{name} and uname like #{uname} and username like #{userName}")
    List<User> getByCondition(@Param("name") String name,@Param("uname") String uname, @Param("userName") String userName);

    @Select("select * from user where id = #{id}")
    User getById(@Param("id") Integer id);

    @Update("update user set name = #{name}, " +
            "uname = #{uname}, " +
            "username = #{userName}, " +
            "password = #{password}, " +
            "checkname = #{checkName}, receivename = #{receiveName}," +
            "code = #{code} where id = #{id}")
    int update(User user);
}
