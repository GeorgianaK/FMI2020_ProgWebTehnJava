package com.sal.demo.aspects;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
public class ErrorBookLogger {

    private Logger logger = Logger.getLogger(ErrorBookLogger.class.getName());

    @Around(value = "execution(* com.sal.demo.service.*.*(..))")
    public void errorLogger(ProceedingJoinPoint joinPoint) throws Throwable {
        try{
            joinPoint.proceed();
        } catch (Exception exception) {
            logger.info("An error occured at " + joinPoint.getSignature().getName() + " with the following message: " + exception.getMessage());
            logger.info("Cause: " + exception.getCause());
        }

    }

    @AfterThrowing(value = "@annotation(BorrowedErrorLogger)", throwing = "exception")
    public void borrowedError(Exception exception) throws Throwable {
        logger.info("ERROR: " +  exception.getMessage());
    }

    @Around(value = "@annotation(ReturnedErrorLogger)")
    public void returningError(ProceedingJoinPoint joinPoint) throws Throwable {
        try{
            joinPoint.proceed();
        } catch (Exception exception) {
            logger.info("Error: " + exception.getMessage());
            logger.info("Located at: " + joinPoint.getSignature().getName());
        }

    }

}
