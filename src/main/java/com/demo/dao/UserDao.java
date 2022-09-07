package com.demo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demo.domain.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserDao extends BaseMapper<User> {

    @Update("update user set name = #{name}, username = #{userName}, password = #{password} where id = #{id}")
    int update(User user);

    @Select("select * from user, pri where id = #{pri.uid}}")
    @Results(
            {
                    @Result(column = "id", property = "id"),
                    @Result(column = "name", property = "name"),
                    @Result(column = "uname", property = "uname"),
                    @Result(column = "username", property = "userName"),
                    @Result(column = "password", property = "password"),
                    @Result(column = "createdate", property = "createDate"),
                    @Result(column = "checkname", property = "checkName"),
                    @Result(column = "receivename", property = "receiveName"),
                    @Result(column = "code", property = "code"),
                    @Result(column = "delete", property = "delete"),
                    @Result(column = "id", property = "privilege", javaType = List.class,
                            one = @One(select = "com.demo.dao.PriDao.selectByUid"))
            }
    )
    User getById(int id);

}
