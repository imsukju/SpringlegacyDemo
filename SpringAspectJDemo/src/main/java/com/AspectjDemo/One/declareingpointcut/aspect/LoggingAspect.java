package com.AspectjDemo.One.declareingpointcut.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component // 이 어노테이션이 적용된 클래스는 Spring IOC에 의해 자동으로 객체를 만들어져서 빈으로 등록된다
public class LoggingAspect {
	@Pointcut("execution(* transfer(..))")
	private void alliaseone() {} // 알리아스임
	
	
    /**
     * @return the pointcut expression where to bind the advice
     */
	@Before("alliaseone") // 알리아스가 정의한 조건을 스트링으로
	public void logBeforeTransfer()
	{
		System.out.println("TEst aspect one");
	}
	


}
	
