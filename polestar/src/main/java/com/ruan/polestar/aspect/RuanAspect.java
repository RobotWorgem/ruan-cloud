package com.ruan.polestar.aspect;

import com.ruan.polestar.annotation.Ruan;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Aspect
@Service
@Slf4j
public class RuanAspect {

    public RuanAspect() {
    }

    @Pointcut("@annotation(com.ruan.polestar.annotation.Ruan)")
    public void aspect() {

    }

    @Around("aspect()&&@annotation(anno)")
    public Object interceptor(ProceedingJoinPoint point, Ruan anno) {

        StringBuilder result = new StringBuilder();
        Object[] arguments = point.getArgs();
        Arrays.stream(arguments).forEach((arg)->{
            log.info("argument: {}", arg);
            result.append(arg);
        });
        log.info(result.toString());
        Object res = null;
        try {
            res = point.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return res;
    }
}
