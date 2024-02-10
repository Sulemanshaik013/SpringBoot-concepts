package com.learning.springaop.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class LoggingAspect {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

//	@Pointcut("within(com.learning.springaop.service..*)")
    @Pointcut("@annotation(LogEntryAndExit)")  
	public void entryAndExitPointcut() {
	}

    @Pointcut("@annotation(LogExceptions)")  
//	@Pointcut("within(com.learning.springaop.service..*) || within(com.learning.springaop.util..*)")
	public void exceptionsPointcut() {
	}

    @Pointcut("@annotation(LogExecutionTime)")  
//	@Pointcut("within(com.learning.springaop.controller..*) ")
	public void executionTimePointcut() {
	}

    @Pointcut("@annotation(LogReturnValues)")  
//	@Pointcut("within(com.learning.springaop.controller..*) ")
	public void returningValuesPointcut() {
	}

	@Around(value = "entryAndExitPointcut()")
	public Object logEntryAndExit(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		// Method entry and exit logic
		MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();

		String className = methodSignature.getDeclaringType().getSimpleName();
		String methodName = methodSignature.getName();

		log.info("Entered into {}.{} ", className, methodName);
		Object result = proceedingJoinPoint.proceed();
		log.info("Exited from {}.{} ", className, methodName);

		return result;
	}

	@AfterThrowing(value = "exceptionsPointcut()", throwing = "e")
	public void logExceptions(JoinPoint joinPoint, Throwable e) throws Throwable {
		// Exception logic
		MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();

		String className = methodSignature.getDeclaringType().getSimpleName();
		String methodName = methodSignature.getName();

		log.error("Exception has been thrown from {}.{} with message {}", className, methodName,
				e.getLocalizedMessage());
	}

	@AfterReturning(value = "returningValuesPointcut()", returning = "object")
	public void logRetuningValues(JoinPoint joinPoint, Object object) throws Throwable {
		// logic
		MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();

		String className = methodSignature.getDeclaringType().getSimpleName();
		String methodName = methodSignature.getName();

		log.info("Returning from {}.{} with {}", className, methodName, object);

	}

	@Around(value = "executionTimePointcut()")
	public Object logExecutionTime(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		// Execution time logic
		MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();

		String className = methodSignature.getDeclaringType().getSimpleName();
		String methodName = methodSignature.getName();

		final StopWatch stopWatch = new StopWatch();

		stopWatch.start();
		Object result = proceedingJoinPoint.proceed();
		stopWatch.stop();
		log.info("Execution time of {}.{} is {}ms", className, methodName, stopWatch.getTotalTimeMillis());

		return result;
	}
}
