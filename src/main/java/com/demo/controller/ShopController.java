package com.demo.controller;

import com.demo.domain.PageBeanShop;
import com.demo.domain.Shop;
import com.demo.domain.User;
import com.demo.service.ShopService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shop")
//跨域问题
@CrossOrigin
public class ShopController {

    @Autowired
    private ShopService shopService;

    @ApiOperation("添加营业厅")
    @PostMapping
    public Result save(@RequestBody Shop shop) {
        boolean flag = shopService.addShop(shop);
        return new Result(flag ? Code.SAVE_OK:Code.SAVE_ERR, flag);
    }

    @ApiOperation("根据id获取营业厅")
    @GetMapping("/check/{id}")
    public Result getById(@PathVariable Integer id) {
        Shop shop = shopService.getById(id);
        /*Integer code = user != null ? Code.GET_OK:Code.GET_ERR;
        String msg = user != null ? "" : "数据查询失败,请重试!";*/
        return new Result(20041, shop, "");
    }

    @ApiOperation("分页查询")
    @GetMapping("/pagelist/{page}/{size}")
    public PageBeanShop pageList(@PathVariable("page") Integer page, @PathVariable("size") Integer size) {

        return shopService.getByPage(page, size);
    }

    @ApiOperation("修改营业厅")
    @PutMapping
    public Result update(@RequestBody Shop shop) {
        boolean flag = shopService.update(shop);
        return new Result(flag ? Code.UPDATE_OK:Code.UPDATE_ERR, flag);
    }

    @ApiOperation("条件查询")
    @GetMapping("/{name}")
    public Result getByCondition(@PathVariable String name) {
        List<Shop> shop = shopService.getByCondition(name);
        Integer code = shop != null ? Code.GET_OK:Code.GET_ERR;
        String msg = shop != null ? "" : "数据查询失败,请重试!";
        return new Result(code, shop, msg);
    }
}
