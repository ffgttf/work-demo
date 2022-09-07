package com.demo.domain;

import lombok.Data;

import java.util.List;

@Data
public class PageBean {
    private List<User> data;
    private Long total;
}
