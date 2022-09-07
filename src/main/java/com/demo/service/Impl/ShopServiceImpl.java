package com.demo.service.Impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.dao.ShopDao;
import com.demo.domain.PageBeanShop;
import com.demo.domain.Shop;
import com.demo.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopServiceImpl implements ShopService {

    @Autowired
    private ShopDao shopDao;
    @Override
    public boolean addShop(Shop shop) {
        return shopDao.insert(shop) > 0;
    }

    @Override
    public boolean update(Shop shop) {
        return shopDao.update(shop) > 0;
    }


    @Override
    public Shop getById(Integer id) {
        Shop shop = shopDao.getById(id);
        return shop;
    }

    @Override
    public List<Shop> getByCondition(String name) {
        if(name != null && name.length() > 0){
            name = ("%"+ name +"%");
        }
        List<Shop> shops = shopDao.getByCondition(name);
        return shops;
    }


    @Override
    public PageBeanShop getByPage(Integer page, Integer size) {
        Page<Shop> shopPage = new Page<>(page, size);
        Page<Shop> resultPage = this.shopDao.selectPage(shopPage, null);

        PageBeanShop pageBean = new PageBeanShop();
        pageBean.setData(resultPage.getRecords());
        pageBean.setTotal(resultPage.getTotal());
        return pageBean;
    }
}
