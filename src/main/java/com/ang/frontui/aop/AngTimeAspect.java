package com.ang.frontui.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

@Aspect
@Component
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class AngTimeAspect {

    @Pointcut("execution(* com.ang.frontui.mapper.DailyTaskMapper+.*(..))")
    public void ttl(){};

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
