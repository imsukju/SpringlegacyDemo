package com.AspectjDemo.One.combinedpointcut.declarepointcut;

import static java.lang.System.out;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import com.AspectjDemo.One.combinedpointcut.aspect.LoggingAspectCombine;
import com.AspectjDemo.One.combinedpointcut.pontcuts.CommonPontCuts;

@Aspect
@Component
public class DeclarePoincuts {
	
	@Before("com.AspectjDemo.One.combinedpointcut.aspect.LoggingAspectCombine.publicWebLayerOperation()")
	public void logBeforePublicWebOperation(JoinPoint jp)
	{
		out.println("logBeforePublicWebOperation");
	}
	
	@Before("com.AspectjDemo.One.combinedpointcut.aspect.LoggingAspectCombine.transactionServiceOperation()")
	public void logBeforeTransactionServiceOperation(JoinPoint jp)
	{
		out.println("TransactionServiceOperation");
		out.println("Method: " + jp.getSignature().getName());
	}
	
	@Before("com.AspectjDemo.One.combinedpointcut.aspect.LoggingAspectCombine.webOrTransacationalServiceOpeation()")
	public void logBeforeWebOrTransacationalServiceOpeation(JoinPoint jp)
	{
		out.println("WebOrTransacationalServiceOpeation");
		out.println("Method: " + jp.getSignature().getName());
	}
}
