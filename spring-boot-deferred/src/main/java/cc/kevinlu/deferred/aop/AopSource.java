package cc.kevinlu.deferred.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
@Configuration
public class AopSource {

    @Pointcut("execution(* cc.kevinlu.deferred.controller.*Controller.*(..))")
    public void excudeService() {
    }

    @Around("excudeService()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("------before-----");
        Object obj = joinPoint.proceed();
        log.info("------after-----");
        return obj;
    }

}
