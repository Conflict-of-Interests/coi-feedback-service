package com.revature.coi.revanauts.aspects;

import java.time.LocalTime;
import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Pointcut("within(com.revature.coi.revanauts..*)")
	public void logAll() { }
	
	@Before("logAll()")
	public void logMethodStart(JoinPoint jp) {
		String methodSig = jp.getTarget().getClass().toString() + "." + jp.getSignature().getName();
		String argStr = Arrays.toString(jp.getArgs());
		logger.info("{} invoked at {}", methodSig, LocalTime.now());
		logger.info("Input arguments: {}", argStr);
	}
	
	@AfterReturning(pointcut="logAll()", returning="rtrn")
	public void logMethodReturn(JoinPoint jp, Object rtrn) {
		String methodSig = jp.getTarget().getClass().toString() + "." + jp.getSignature().getName();
		logger.info("{} successfully returned at {}", methodSig, LocalTime.now());
		logger.info("Object returned: {}", rtrn);
	}
	
	@AfterThrowing(pointcut="logAll()", throwing="e")
	public void errorOccurrence(JoinPoint jp, Exception e) {
		String methodSig = jp.getTarget().getClass().toString() + "." + jp.getSignature().getName();
		logger.info("{} thrown in method: {}", e.getMessage(), methodSig);
	}
}
