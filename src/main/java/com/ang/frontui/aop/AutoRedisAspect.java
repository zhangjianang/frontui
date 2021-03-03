package com.ang.frontui.aop;//package com.xdf.report.aspect;

import com.alibaba.fastjson.JSONObject;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

@Aspect
@Component
@Ignore
public class AutoRedisAspect {

    @Autowired
    private StringRedisTemplate template;

    @Autowired
    RedissonClient redissonClient;

    @Pointcut("@annotation( com.ang.frontui.aop.MyRedisCache)")
    public void redisPointCut() {
    }

    @Around("redisPointCut()")
    public Object arouond(ProceedingJoinPoint point) throws Throwable {
        //用的最多通知的签名
        Signature signature = point.getSignature();
        MethodSignature msg = (MethodSignature) signature;
        Object target = point.getTarget();
        //获取注解标注的方法
        Method method = target.getClass().getMethod(msg.getName(), msg.getParameterTypes());
        //通过方法获取注解
        MyRedisCache annotation = method.getAnnotation(MyRedisCache.class);

        StringBuilder mykeybuilder = new StringBuilder(method.getName());
        if(!StringUtils.isEmpty(annotation.key())){
            mykeybuilder.append(":"+annotation.key());
        }Object[] args = point.getArgs();
        for(Object per:args){
            mykeybuilder.append(":"+per.toString());
        }
        if(annotation.isUserConn()){
//            mykeybuilder.append(":"+ ReportThreadLocal.getLoginUserEmail());
        }

        Class returnType = annotation.returnType();

        String redisValue = template.opsForValue().get(mykeybuilder.toString());

        if (StringUtils.isEmpty(redisValue)) {
            RLock lock = redissonClient.getLock("LOCK:" + mykeybuilder.toString());
            try {
                boolean res = lock.tryLock(10, TimeUnit.SECONDS);
                if (res) {
                    redisValue = template.opsForValue().get(mykeybuilder.toString());
                    if (StringUtils.isEmpty(redisValue)) {
                        Object result = point.proceed();
                        System.out.println(point.getTarget().getClass().getName()+"."+ method.getName() + " 获取");
                        template.opsForValue().set(mykeybuilder.toString(), JSONObject.toJSONString(result), expireTime(0), TimeUnit.MINUTES);
                        return result;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
        System.out.println(mykeybuilder.toString()+" redis 获取");
        if (Object.class != returnType) {
            if (annotation.isArray()) {
                return JSONObject.parseArray(redisValue, returnType);
            } else {
                return JSONObject.parseObject(redisValue, returnType);
            }
        }
        return redisValue;
    }


    private int expireTime(int type){
        return 10;
    }

}
