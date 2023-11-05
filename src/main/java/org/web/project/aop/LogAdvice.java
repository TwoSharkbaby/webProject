package org.web.project.aop;

import lombok.extern.log4j.Log4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
@Log4j
public class LogAdvice {

    @Before("execution(* org.web.project.service.SampleService*.*(..))")
    public void logBefore(){
        log.info("=================================");
    }

    @AfterThrowing(pointcut = "execution(* org.web.project.service.SampleService*.*(..))", throwing = "exception")
    public void logException(Exception exception){
        log.info("Exception...!!!");
        log.info("exception: " + exception);
    }

    @Around("execution(* org.web.project.service.SampleService*.*(..))")
    public Object logTime(ProceedingJoinPoint proceedingJoinPoint){
        long start = System.currentTimeMillis();
        log.info("Target: " + proceedingJoinPoint.getTarget());
        log.info("Parm: " + Arrays.toString(proceedingJoinPoint.getArgs()));

        Object result = null;

        try {
            result = proceedingJoinPoint.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
        }

        long end = System.currentTimeMillis();
        log.info("Time: " + ( end - start ));

        return result;
    }
}
