package com.roncoo.eshop.cache.kafka;

import com.alibaba.fastjson.JSONObject;
import com.roncoo.eshop.cache.model.ProductInfo;
import com.roncoo.eshop.cache.model.ShopInfo;
import com.roncoo.eshop.cache.service.CacheService;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @Auther: tianyu
 * @Date: 2019/1/6 20:49
 * @Description:  cache-message主题消息监听
 */
@Component
@Slf4j
public class CacheMessageListener {

    @Resource
    private CacheService cacheService;

    /**
     * 实时获取kafka数据(生产一条，监听生产topic自动消费一条)
     * @param record
     * @throws IOException
     */
    @KafkaListener(topics = {"${kafka.consumer.topic}"})
    public void listen(ConsumerRecord<?, ?> record) throws IOException {
        String value = (String) record.value();
        // 首先将message转换成json对象
        JSONObject messageJSONObject = JSONObject.parseObject(value);

        // 从这里提取出消息对应的服务的标识
        String serviceId = messageJSONObject.getString("serviceId");

        // 如果是商品信息服务
        if("productInfoService".equals(serviceId)) {
            processProductInfoChangeMessage(messageJSONObject);
        } else if("shopInfoService".equals(serviceId)) {
            processShopInfoChangeMessage(messageJSONObject);
        }
    }

    /**
     * 处理商品信息变更的消息
     * @param messageJSONObject
     */
    private void processProductInfoChangeMessage(JSONObject messageJSONObject) {
        // 提取出商品id
        Integer productId = messageJSONObject.getInteger("productId");

        // 调用商品信息服务的接口
        // 直接用注释模拟：getProductInfo?productId=1，传递过去
        // 商品信息服务，一般来说就会去查询数据库，去获取productId=1的商品信息，然后返回回来

        // 龙果有分布式事务的课程，主要讲解的分布式事务几种解决方案，里面也涉及到了一些mq，或者其他的一些技术，但是那些技术都是浅浅的给你搭建一下，使用
        // 你从一个课程里，还是学到的是里面围绕的讲解的一些核心的知识
        // 缓存架构：高并发、高性能、海量数据，等场景

        ProductInfo productInfo = cacheService.findProductInfoByIdFromDB(productId);
        cacheService.saveProductInfo2LocalCache(productInfo);
        System.out.println("===================获取刚保存到本地缓存的商品信息：" + cacheService.getProductInfoFromLocalCache(productId));
        cacheService.saveProductInfo2ReidsCache(productInfo);
    }

    /**
     * 处理店铺信息变更的消息
     * @param messageJSONObject
     */
    private void processShopInfoChangeMessage(JSONObject messageJSONObject) {
        // 提取出店铺id
        Long shopId = messageJSONObject.getLong("shopId");

        // 调用商品信息服务的接口
        // 直接用注释模拟：getProductInfo?productId=1，传递过去
        // 商品信息服务，一般来说就会去查询数据库，去获取productId=1的商品信息，然后返回回来

        // 龙果有分布式事务的课程，主要讲解的分布式事务几种解决方案，里面也涉及到了一些mq，或者其他的一些技术，但是那些技术都是浅浅的给你搭建一下，使用
        // 你从一个课程里，还是学到的是里面围绕的讲解的一些核心的知识
        // 缓存架构：高并发、高性能、海量数据，等场景

        ShopInfo shopInfo = cacheService.findShopInfoByIdFromDB(shopId);
        cacheService.saveShopInfo2LocalCache(shopInfo);
        System.out.println("===================获取刚保存到本地缓存的店铺信息：" + cacheService.getShopInfoFromLocalCache(shopId));
        cacheService.saveShopInfo2ReidsCache(shopInfo);
    }
}
