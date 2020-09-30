package com.nokia.esp.core.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 
 * The Global Logging Mechanism for File Store Microservice
 *
 */
@Aspect
@Component
public class LoggingAspect {

	/** The logger. */
	private static final Logger LOGGER = LoggerFactory.getLogger(LoggingAspect.class);

	/**
	 * Global Logging for the File Store Microservice
	 * 
	 * @param joinPoint
	 * @return Object
	 * 
	 * @throws Throwable
	 */
	@Around("execution(* com.nokia.esp.upload..*(..))")
	public Object loggerAroundAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
		long sratTime = System.currentTimeMillis();
		LOGGER.debug("START: {}.{} --- ARGS: {}", joinPoint.getSignature().getDeclaringTypeName(),
				joinPoint.getSignature().toShortString(), joinPoint.getArgs());
		Object obj = joinPoint.proceed();
		LOGGER.debug("END: {}.{} --- RETURN: {} --- DURATION: {}ms", joinPoint.getSignature().getDeclaringTypeName(),
				joinPoint.getSignature().toShortString(), obj, System.currentTimeMillis() - sratTime);
		return obj;
	}
}
