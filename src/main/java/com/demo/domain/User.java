package com.demo.domain;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.sql.Date;
import java.util.List;

@Data
@TableName("user")
public class User {
    private Integer id;
    private String name;
    private String uname;
    @TableField(value = "username")
    private String userName;
    private String password;
    @TableField(value = "createdate")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createDate;
    @TableField(value = "checkname")
    private String checkName;
    @TableField(value = "receivename")
    private String receiveName;
    private String code;
    @TableLogic(value = "0", delval = "1")
    private Integer deleted;

    @TableField(exist = false)
    private List<Privilege> privilege;
}
