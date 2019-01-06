package com.roncoo.eshop.cache.spring;

import org.springframework.context.ApplicationContext;

/**
 * @Author: tianyu
 * @Date: 2019/1/6 13:28
 * @Description:
 */
public class SpringContext {

    private static ApplicationContext applicationContext;

    public static ApplicationContext getApplicationContext() {
        return SpringContext.applicationContext;
    }

    public static void  setApplicationContext(ApplicationContext context) {
        SpringContext.applicationContext = context;
    }
}
