package com.AspectjDemo.One.combinedpointcut.aspect;

import static java.lang.System.out;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
public class LoggingAspectCombine {
	
	@Pointcut("com.AspectjDemo.One.combinedpointcut.pontcuts.CommonPontCuts.inWebLayer()"
			+"&& execution(public * hanldleRequest(..))")
	public void publicWebLayerOperation() {}
	
	@Pointcut("com.AspectjDemo.One.combinedpointcut.pontcuts.CommonPontCuts.businessLayer()"
			+"&& !com.AspectjDemo.One.combinedpointcut.pontcuts.CommonPontCuts.inDataAccessLayer()")
	public void transactionServiceOperation() {}
	
	@Pointcut("transactionServiceOperation() || publicWebLayerOperation()")
	public void webOrTransacationalServiceOpeation() {}
	
	
	

}
