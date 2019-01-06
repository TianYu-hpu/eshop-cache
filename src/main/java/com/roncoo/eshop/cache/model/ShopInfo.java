package com.roncoo.eshop.cache.model;

import lombok.Data;

/**
 * @Auther: tianyu
 * @Date: 2019/1/6 13:35
 * @Description:  店铺信息
 */
@Data
public class ShopInfo {

    private Long id;
    private String name;
    private Integer level;
    private Double goodCommentRate;

}
