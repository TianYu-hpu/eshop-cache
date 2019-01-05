package com.roncoo.eshop.cache.service.impl;

import com.roncoo.eshop.cache.model.ProductInfo;
import com.roncoo.eshop.cache.service.CacheService;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @Auther: tianyu
 * @Date: 2019/1/5 21:51
 * @Description:  缓存Service实现类
 */
@Service
public class CacheServiceImpl implements CacheService {

    @CachePut(value = "local", key = "'key_' +#productInfo.getId()")
    @Override
    public ProductInfo saveProductInfo(ProductInfo productInfo) {
        return productInfo;
    }

    @Cacheable(value = "local", key = "'key_' +#id")
    @Override
    public ProductInfo findById(long id) {
        return null;
    }
}
