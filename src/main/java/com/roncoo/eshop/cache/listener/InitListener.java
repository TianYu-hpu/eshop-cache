package com.roncoo.eshop.cache.listener;

import com.roncoo.eshop.cache.spring.SpringContext;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @Auther: tianyu
 * @Date: 2019/1/6 13:20
 * @Description:
 */
public class InitListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext sc = sce.getServletContext();
        ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(sc);
        SpringContext.setApplicationContext(context);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }


}
