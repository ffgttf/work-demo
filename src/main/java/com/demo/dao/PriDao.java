package com.demo.dao;

import com.demo.domain.Privilege;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PriDao {

    @Select("select * from pri where uid = #{uid}")
    List<Privilege> selectByUid(int uid);
}
