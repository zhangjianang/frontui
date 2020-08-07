package com.ang.frontui.common;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class RedisAspect {

    @Autowired
    RedisTemplate<String,  String> redisTemplate;

    @Around("@annotation(com.ang.frontui.common.AngRedisNotify)")
    public Object aroundAnnotation(ProceedingJoinPoint pjp) throws Throwable {
        Object object = pjp.proceed();
        return object;
    }

    @After("@annotation(com.ang.frontui.common.AngRedisNotify)")
    public void afterAnotation(JoinPoint jp){
        Object[] args = jp.getArgs();
        String key = "id-"+args[0];
        System.out.println("删除key"+key);
        redisTemplate.delete(key);
    }
}
