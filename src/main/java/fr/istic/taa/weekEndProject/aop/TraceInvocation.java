package fr.istic.taa.weekEndProject.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TraceInvocation {

	@AfterReturning("execution(* fr.istic.taa.WeekEndProject.service.*ServiceImpl.*(..))")
	public void logServiceAfter(JoinPoint joinPoint) {
		System.out.println("Completed: " + joinPoint.getSignature().getName());
		
	}

	@Before("execution(* fr.istic.taa.WeekEndProject.service.*ServiceImpl.*(..))")
	public void logServiceBefore(JoinPoint joinPoint) {
		System.out.println("Begin: " + joinPoint.getSignature().getName());
		
	}
	
	
}