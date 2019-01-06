package com.roncoo.eshop.cache.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.roncoo.eshop.cache.model.ProductInfo;
import com.roncoo.eshop.cache.model.ShopInfo;
import com.roncoo.eshop.cache.service.CacheService;
import com.roncoo.eshop.cache.mapper.ProductInfoMapper;
import com.roncoo.eshop.cache.mapper.ShopInfoMapper;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisCluster;

import javax.annotation.Resource;

/**
 * @Auther: tianyu
 * @Date: 2019/1/5 21:51
 * @Description:  缓存Service实现类
 */
@Service("cacheService")
public class CacheServiceImpl implements CacheService {

    public static final String CACHE_NAME = "local";

    @Resource
    private JedisCluster jedisCluster;
    @Resource
    private ProductInfoMapper productInfoMapper;
    @Resource
    private ShopInfoMapper shopInfoMapper;

    /**
     * 将商品信息保存到本地缓存中
     * @param productInfo
     * @return
     */
    @Override
    @CachePut(value = CACHE_NAME, key = "'product_info_'+#productInfo.getId()")
    public ProductInfo saveLocalCache(ProductInfo productInfo) {
        return productInfo;
    }

    /**
     * 从本地缓存中获取商品信息
     * @param id
     * @return
     */
    @Override
    @Cacheable(value = CACHE_NAME, key = "'product_info_'+#id")
    public ProductInfo getLocalCache(Long id) {
        return null;
    }

    /**
     * 将商品信息保存到本地的ehcache缓存中
     * @param productInfo
     */
    @Override
    @CachePut(value = CACHE_NAME, key = "'product_info_'+#productInfo.getId()")
    public ProductInfo saveProductInfo2LocalCache(ProductInfo productInfo) {
        return productInfo;
    }

    /**
     * 从本地ehcache缓存中获取商品信息
     * @param productId
     * @return
     */
    @Override
    @Cacheable(value = CACHE_NAME, key = "'product_info_'+#productId")
    public ProductInfo getProductInfoFromLocalCache(Integer productId) {
        return null;
    }

    /**
     * 将店铺信息保存到本地的ehcache缓存中
     * @param shopInfo
     */
    @Override
    @CachePut(value = CACHE_NAME, key = "'shop_info_'+#shopInfo.getId()")
    public ShopInfo saveShopInfo2LocalCache(ShopInfo shopInfo) {
        return shopInfo;
    }

    /**
     * 从本地ehcache缓存中获取店铺信息
     * @param shopId
     * @return
     */
    @Override
    @Cacheable(value = CACHE_NAME, key = "'shop_info_'+#shopId")
    public ShopInfo getShopInfoFromLocalCache(Long shopId) {
        return null;
    }

    /**
     * 将商品信息保存到redis中
     * @param productInfo
     */
    @Override
    public void saveProductInfo2ReidsCache(ProductInfo productInfo) {
        String key = "product_info_" + productInfo.getId();
        jedisCluster.set(key, JSONObject.toJSONString(productInfo));
    }

    /**
     * 将店铺信息保存到redis中
     * @param shopInfo
     */
    @Override
    public void saveShopInfo2ReidsCache(ShopInfo shopInfo) {
        String key = "shop_info_" + shopInfo.getId();
        jedisCluster.set(key, JSONObject.toJSONString(shopInfo));
    }

    @Override
    public ProductInfo findProductInfoByIdFromDB(Integer id) {
        return productInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public ShopInfo findShopInfoByIdFromDB(Long id) {
        return shopInfoMapper.selectByPrimaryKey(id);
    }
}
