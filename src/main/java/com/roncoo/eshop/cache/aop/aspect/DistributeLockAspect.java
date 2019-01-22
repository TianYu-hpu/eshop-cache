package com.roncoo.eshop.cache.aop.aspect;

import com.roncoo.eshop.cache.zk.ZooKeeperSession;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Auther: tianyu
 * @Date: 2019/1/22 20:49
 * @Description:
 */
@Component
@Slf4j
@Aspect
public class DistributeLockAspect {

    @Resource
    private ZooKeeperSession zooKeeperSession;

    @Around(value = "execution(* com.roncoo.eshop.cache..*.*(..)) && @annotation(com.roncoo.eshop.cache.aop.annotation.DistributeLock) && args(name,..)")
    public Object process(ProceedingJoinPoint pjp, String name) throws Throwable {
        zooKeeperSession.lock(name);
        Object result = pjp.proceed();
        zooKeeperSession.unlock(name);
        return result;
    }

}
