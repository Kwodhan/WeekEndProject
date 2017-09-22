package fr.istic.taa.WeekEndProject.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class SecurityAspect {

	
	@Around("")
	public Object check(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		
		return proceedingJoinPoint.proceed();
		
	}
}
