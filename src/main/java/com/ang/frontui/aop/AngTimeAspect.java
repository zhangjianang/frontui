package com.ang.frontui.aop;

import com.alibaba.fastjson.JSONObject;
import com.ang.frontui.service.DailyService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import static com.ang.frontui.controller.DailyController.DAILY_KEY;

@Aspect
@Component
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class AngTimeAspect {

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    DailyService dailyService;

    @Pointcut("execution(* com.ang.frontui.mapper.DailyTaskMapper+.*(..))")
    public void ttl(){};

    @Pointcut("execution(* com.ang.frontui.mapper.DailyTaskMapper+.addOne(..))" +
            "|| execution(* com.ang.frontui.mapper.DailyTaskMapper+.updateById(..))")
    public void upoint(){};

    @After("upoint()")
    public void after(){
        String s = JSONObject.toJSONString(dailyService.findAll());
        redisTemplate.opsForValue().set(DAILY_KEY,s);
        System.out.println("更新信息");
    }

    @Around("ttl()")
    public Object aroundAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().getName();
        long st = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long elapse = System.currentTimeMillis() - st;
        System.out.println(methodName+" 花费 "+elapse);
        return result;
    }
}
