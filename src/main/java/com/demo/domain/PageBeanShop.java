package com.demo.domain;

import lombok.Data;

import java.util.List;

@Data
public class PageBeanShop {
    private List<Shop> data;
    private Long total;
}
