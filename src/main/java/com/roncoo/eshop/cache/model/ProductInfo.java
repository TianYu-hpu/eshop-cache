package com.roncoo.eshop.cache.model;

import lombok.Data;

/**
 * @Auther: tianyu
 * @Date: 2019/1/5 21:48
 * @Description:  产品信息
 */
@Data
public class ProductInfo {

    private Long id;
    private String name;
    private Double price;

    public ProductInfo(Long id, String name, Double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public ProductInfo() {
    }
}
