package com.state.psa.community.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class LoggingAspect {
    private static final Logger LOGGER = LogManager.getLogger(LoggingAspect.class);

    @Around("execution(* com.state.psa.community.controller..*(..)))")
    public Object logMethodExecutionTime(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
        final StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Object result = proceedingJoinPoint.proceed();
        stopWatch.stop();
        LOGGER.info("Execution time of "
                + methodSignature.getDeclaringType().getSimpleName()
                + "." + methodSignature.getName() + " "
                + ":: " + stopWatch.getTotalTimeMillis() + " ms");

        return result;
    }

    @Before("execution(* com.state.psa.community.service..*(..)))")
    public void logBeforeAllMethodCall(JoinPoint joinPoint) throws Throwable {
        LOGGER.info("Started with method : " + joinPoint.getSignature().getName());
    }

    @After("execution(* com.state.psa.community.service..*(..)))")
    public void logAfterAllMethodCall(JoinPoint joinPoint) throws Throwable {
        LOGGER.info("Completed execution of method :" + joinPoint.getSignature().getName());
    }
}

