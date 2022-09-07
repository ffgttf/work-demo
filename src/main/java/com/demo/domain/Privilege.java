package com.demo.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("pri")
public class Privilege {

    @TableField(value = "charac")
    private String charac;
    @TableField(value = "enter")
    private String enter;
    @TableField(value = "user")
    private String user;

}
