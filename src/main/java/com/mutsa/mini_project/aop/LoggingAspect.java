package com.mutsa.mini_project.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

    /**
     * service && Repository logging
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around("execution(* com.mutsa.mini_project.service..*(..)) " +
            "|| execution(* com.mutsa.mini_project.repository..*(..))")
    public Object serviceTaskTime(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();

        String className = methodSignature.getDeclaringType().getSimpleName();
        String methodName = methodSignature.getName();
        log.info("{}->{} Start", className, methodName);

        StopWatch stopWatch = new StopWatch(className + "->" + methodName);
        stopWatch.start(methodName);
        try {
            return joinPoint.proceed();
        }
        finally {
            stopWatch.stop();
            log.info("{} running time= {}s", stopWatch.getId(), stopWatch.getTotalTimeSeconds());
            log.info("{}->{} End", className, methodName);
        }
    }

    /**
     * Controller logging
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around("execution(* com.mutsa.mini_project.contorller..*(..))")
    public Object logController(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();

        // Get intercepted method details
        String className = methodSignature.getDeclaringType().getSimpleName();
        String methodName = methodSignature.getName();
        log.info("{}->{} Start", className, methodName);
        try {
            return joinPoint.proceed();
        }
        finally {
            log.info("{}->{} End", className, methodName);
        }
    }
}
