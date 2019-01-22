package com.roncoo.eshop.cache.aop.annotation;

import java.lang.annotation.*;

/**
 * @Auther: tianyu
 * @Date: 2019/1/22 20:49
 * @Description:  基于zookeeper实现的分布式锁
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DistributeLock {
}
