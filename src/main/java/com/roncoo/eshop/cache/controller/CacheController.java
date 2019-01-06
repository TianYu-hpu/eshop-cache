package com.roncoo.eshop.cache.controller;

import com.roncoo.eshop.cache.model.ProductInfo;
import com.roncoo.eshop.cache.service.CacheService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @Auther: tianyu
 * @Date: 2019/1/5 21:56
 * @Description:  缓存Controller
 */
@Controller
public class CacheController {

    @Resource
    private CacheService cacheService;

    @RequestMapping("/testPutCache")
    @ResponseBody
    public void testPutCache(ProductInfo productInfo) {
        System.out.println(productInfo.getId() + ":" + productInfo.getName());
        cacheService.saveProductInfo2ReidsCache(productInfo);
    }

    @RequestMapping("/testGetCache")
    @ResponseBody
    public ProductInfo testGetCache(Integer id) {
        ProductInfo productInfo = cacheService.getProductInfoFromLocalCache(id);
        System.out.println(productInfo.getId() + ":" + productInfo.getName());
        return productInfo;
    }
}
