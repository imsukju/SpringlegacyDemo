package com.configurableOne;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.configurableOne.config.Appconfigrable;
import com.configurableOne.dao.AccountSpringConfigurable;
import com.configurableOne.service.EntitlementCalculationService;
import com.configurableOne.service.StubEntilementCallculationService;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        ApplicationContext context = new AnnotationConfigApplicationContext(Appconfigrable.class);

        // 빈이 아닌 객체
        AccountSpringConfigurable account = new AccountSpringConfigurable();
        account.dosometing();  // AspectJ에 의해 어드바이스가 적용됩니다.

        // 스프링 빈
        EntitlementCalculationService service = context.getBean(EntitlementCalculationService.class);
        service.Entitlement();  // AspectJ 또는 Spring AOP에 의해 어드바이스가 적용됩니다.
        

        // 빈이 아닌 객체
        EntitlementCalculationService service2 = new StubEntilementCallculationService();
        service2.Entitlement();  // AspectJ에 의해 어드바이스가 적용됩니다.

	}

}
