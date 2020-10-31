package com.sal.demo.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.logging.Logger;

@Aspect
@Component
public class InfoBookLogger {

    private Logger logger = Logger.getLogger(InfoBookLogger.class.getName());

    @Around("execution(* com.sal.demo.service.*.*(..))")
    public void logTimeInfo(ProceedingJoinPoint joinPoint) throws Throwable {
        String name = joinPoint.getSignature().getName();
        Object []arguments = joinPoint.getArgs();
        LocalTime start = LocalTime.now();
        logger.info("TIME: " + LocalDateTime.now() + "         PROCESS: " + name + "  IS STARTING EXECUTING... with PARAMS: " + Arrays.asList(arguments));
        joinPoint.proceed();
        LocalTime end = LocalTime.now();
        logger.info("TIME: " + LocalDateTime.now() + "         PROCESS: " + name + "  FINISHED EXECUTION!");
        logger.info("EXECUTION TIME:  " + start.until(end, ChronoUnit.HOURS) + " hours/ " + start.until(end, ChronoUnit.MINUTES) + " minutes/ " + start.until(end, ChronoUnit.SECONDS) + " seconds");
    }



}
