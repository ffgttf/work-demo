package com.demo.service;

import com.demo.domain.PageBean;
import com.demo.domain.PageBeanShop;
import com.demo.domain.Shop;
import com.demo.domain.User;

import java.util.List;

public interface ShopService {
    boolean addShop(Shop shop);

    Shop getById(Integer id);

    boolean update(Shop shop);

    PageBeanShop getByPage(Integer page, Integer size);

    List<Shop> getByCondition(String name);


}
