package com.demo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demo.domain.Shop;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface ShopDao extends BaseMapper<Shop> {
    @Select("select * from shop where id = #{id}")
    Shop getById(@Param("id") Integer id);

    @Update("update shop set code = #{code} , name = #{name} where id = #{id}")
    int update(Shop shop);

    @Select("select * from shop where name like #{name}")
    List<Shop> getByCondition(String name);
}
