package com.roncoo.eshop.cache.service;

import com.roncoo.eshop.cache.model.ProductInfo;
import com.roncoo.eshop.cache.model.ShopInfo;

/**
 * @Auther: tianyu
 * @Date: 2019/1/5 21:39
 * @Description:
 */
public interface CacheService {

    /**
     * 将商品信息保存到本地缓存中
     * @param productInfo
     * @return
     */
    ProductInfo saveLocalCache(ProductInfo productInfo);

    /**
     * 从本地缓存中获取商品信息
     * @param id
     * @return
     */
    ProductInfo getLocalCache(Long id);

    /**
     * 将商品信息保存到本地的ehcache缓存中
     * @param productInfo
     */
    ProductInfo saveProductInfo2LocalCache(ProductInfo productInfo);

    /**
     * 从本地ehcache缓存中获取商品信息
     * @param productId
     * @return
     */
    ProductInfo getProductInfoFromLocalCache(Integer productId);

    /**
     * 将店铺信息保存到本地的ehcache缓存中
     * @param shopInfo
     */
    ShopInfo saveShopInfo2LocalCache(ShopInfo shopInfo);

    /**
     * 从本地ehcache缓存中获取店铺信息
     * @param shopId
     * @return
     */
    ShopInfo getShopInfoFromLocalCache(Long shopId);

    /**
     * 将商品信息保存到redis中
     * @param productInfo
     */
    void saveProductInfo2ReidsCache(ProductInfo productInfo);

    /**
     * 将店铺信息保存到redis中
     * @param shopInfo
     */
    void saveShopInfo2ReidsCache(ShopInfo shopInfo);

    /**
     * 从数据库中获得产品信息
     * @param id
     * @return
     */
    ProductInfo findProductInfoByIdFromDB(Integer id);

    /**
     * 从数据库中获得店铺信息
     * @param id
     * @return
     */
    ShopInfo findShopInfoByIdFromDB(Long id);

    /**
     * 从redis缓存红获取店铺信息
     * @param shopId
     * @return
     */
    ShopInfo getShopInfoFromReidsCache(Long shopId);

    /**
     * 从redis缓存中获取商品信息
     * @param productId
     * @return
     */
    ProductInfo getProductInfoFromReidsCache(Integer productId);
}
