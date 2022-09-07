package com.demo.controller;

import com.demo.domain.PageBean;
import com.demo.domain.User;
import com.demo.service.EmpService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/emp")
//跨域问题
@CrossOrigin
public class EmpController {

    @Autowired
    private EmpService empService;

    @ApiOperation("添加用户")
    @PostMapping
    public Result save(@RequestBody User user) {
        boolean flag = empService.addUser(user);
        return new Result(flag ? Code.SAVE_OK:Code.SAVE_ERR, flag);
    }

    /*@GetMapping
    public Result getAll() {
        List<User> users = empService.getAll();
        Integer code = users != null ? Code.GET_OK:Code.GET_ERR;
        String msg = users != null ? "" : "数据查询失败,请重试!";
        return new Result(code, users, msg);
    }*/

    @ApiOperation("条件查询")
    @GetMapping("/{name}/{uname}/{userName}")
    public Result getByCondition(@PathVariable String name,@PathVariable String uname,@PathVariable String userName) {
        List<User> users = empService.getByCondition(name, uname, userName);
        Integer code = users != null ? Code.GET_OK:Code.GET_ERR;
        String msg = users != null ? "" : "数据查询失败,请重试!";
        return new Result(code, users, msg);
    }

    @ApiOperation("根据id查询")
    @GetMapping("/check/{id}")
    public Result getById(@PathVariable Integer id) {
        User user = empService.getById(id);
        /*Integer code = user != null ? Code.GET_OK:Code.GET_ERR;
        String msg = user != null ? "" : "数据查询失败,请重试!";*/
        return new Result(20041, user, "");
    }

    @ApiOperation("根据id删除")
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        boolean flag = empService.delete(id);
        return new Result(flag ? Code.DELETE_OK:Code.DELETE_ERR, flag);
    }

    @ApiOperation("分页查询")
    @GetMapping("/pagelist/{page}/{size}")
    public PageBean pageList(@PathVariable("page") Integer page, @PathVariable("size") Integer size) {

        return empService.getByPage(page, size);
    }

    @ApiOperation("修改用户")
    @PutMapping
    public Result update(@RequestBody User user) {
        boolean flag = empService.update(user);
        return new Result(flag ? Code.UPDATE_OK:Code.UPDATE_ERR, flag);
    }
}
