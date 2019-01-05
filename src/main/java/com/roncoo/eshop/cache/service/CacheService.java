package com.roncoo.eshop.cache.service;

import com.roncoo.eshop.cache.model.ProductInfo;

/**
 * @Auther: tianyu
 * @Date: 2019/1/5 21:39
 * @Description:
 */
public interface CacheService {

    ProductInfo saveProductInfo(ProductInfo productInfo);

    ProductInfo findById(long id);

}
