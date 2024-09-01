package com.AspectjDemo.One.combinedpointcut.pontcuts;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class CommonPontCuts {
	
	@Pointcut("within(com.AspectjDemo.One.combinedpointcut.web..*)")
	public void inWebLayer() {}
	
	@Pointcut("within(com.AspectjDemo.One.combinedpointcut.service..*)")
	public void inServiceLayer() {}
	
	@Pointcut("within(com.AspectjDemo.One.combinedpointcut.dao..*)")
	public void inDataAccessLayer() {}
	
	// service.* : service 패키지 내의 모든 클래스
	// service.*.*(..) : 모든 메서드[파라미터 제한 없음]
	@Pointcut("execution(* com.AspectjDemo.One.combinedpointcut.service.*.*(..))")
	public void businessLayer() {}
	
	@Pointcut("execution(* com.AspectjDemo.One.combinedpointcut.dao.*.*(..))")
	public void dataAccessOperation() {}
	

}
