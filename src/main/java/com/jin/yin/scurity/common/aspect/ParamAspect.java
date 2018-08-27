package com.jin.yin.scurity.common.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Arrays;



/**
 * @author: liangjinyin
 * @Date: 2018-08-27
 * @Description:
 */
@Slf4j
@Component
@Aspect
public class ParamAspect {
    /**
     * 获取方法执行时间，和方法参数
     * @param point
     * @return
     * @throws Throwable
     */
    @Around("execution(* com.jin.yin.scurity.modelus.system..*Controller.*(..))")
    public Object paramHandle(ProceedingJoinPoint point) throws Throwable {
        //或去执行方法和参数
        log.info("执行方法为：{}" ,
                point.getSignature().getDeclaringTypeName() +
                        "." + point.getSignature().getName());
        log.info("参数为：{}" , Arrays.toString(point.getArgs()));

        //获取方法执行时间
        long start = System.currentTimeMillis();
        Object object = point.proceed();
        long times = System.currentTimeMillis() - start;
        log.info("执行方法耗时：{} ", times);
        return object;
    }

    /**
     * 拦截方法，并log方法参数
     * @param point
     */
    /*
    @Before("execution(* com.jin.yin.scurity.modelus.system..*Controller.*(..))")
    public void permissionCheck(JoinPoint point) {
        log.info("@Before：目标方法为：{}" ,
                point.getSignature().getDeclaringTypeName() +
                "." + point.getSignature().getName());
        log.info("@Before：参数为：{}" , Arrays.toString(point.getArgs()));
    }*/
}

