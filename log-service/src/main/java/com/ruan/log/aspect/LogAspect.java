package com.ruan.log.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Service;

@Aspect
@Service
@Slf4j
public class LogAspect {

    public LogAspect() {
    }

    @Pointcut("execution(* com.ruan..*.*(..))")
    public void tt() {
    }

    @Around("tt()")
    public Object log(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();

        log.info("日志打印，参数：{}", args.toString());
        Object result = null;
        result = joinPoint.proceed();
        return result;
    }
}
