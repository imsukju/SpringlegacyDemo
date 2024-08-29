package com.sj0829.UsingAutoProxy.config;

import org.springframework.aop.framework.autoproxy.BeanNameAutoProxyCreator;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.aop.interceptor.SimpleTraceInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.AnnotationTransactionAttributeSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.interceptor.TransactionAttributeSourceAdvisor;
import org.springframework.transaction.interceptor.TransactionInterceptor;

import com.sj0829.UsingAutoProxy.Transaction.SimpleTransactionManagerUsingAutoProxy;
import com.sj0829.UsingAutoProxy.bean.BussinessObejst1;
import com.sj0829.UsingAutoProxy.bean.BussinessObejst12;
import com.sj0829.UsingAutoProxy.bean.Mybean;
import com.sj0829.UsingAutoProxy.service.BusinessService;

@Configuration
@EnableTransactionManagement	
public class AppconfigUsingAutoProxy //얘 또한 sping ioc 컨테이너에 의해 Bean으로 등록
{
	
    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        return new DefaultAdvisorAutoProxyCreator();
    }

	@Bean 
	public Mybean myBean()
	{
		return new Mybean();
	}
	
	@Bean
	public Mybean onlybean()
	{
		return new Mybean();
	}
	
	// 모든 빈을 만들고 string 이름을 찾는다??
    @Bean
    public static BeanNameAutoProxyCreator beanNameAutoProxyCreator() {
        BeanNameAutoProxyCreator creator = new BeanNameAutoProxyCreator();
        creator.setBeanNames("only*", "myBean");
        creator.setInterceptorNames("myInterceptor");
        return creator;
    }
    @Bean
    public TransactionAttributeSourceAdvisor transactionAdvisor(TransactionInterceptor transactionInterceptor) {
        TransactionAttributeSourceAdvisor advisor = new TransactionAttributeSourceAdvisor();
        advisor.setTransactionInterceptor(transactionInterceptor);
        return advisor;
    }
    
	@Bean 
	public SimpleTraceInterceptor myInterceptor()
	{
		return new SimpleTraceInterceptor();
	}
	
    @Bean
    public BusinessService businessObject1() {
        return new BussinessObejst1();
    }

    @Bean
    public BusinessService businessObject2() {
        return new BussinessObejst12();
    }

    @Bean
    public TransactionInterceptor transactionInterceptor() {
        // TransactionInterceptor 설정 로직
    	TransactionInterceptor tri = new TransactionInterceptor();
    	tri.setTransactionManager(tranmanager());
    	tri.setTransactionAttributeSource(new AnnotationTransactionAttributeSource());
        return tri;
    }
    
    @Bean
    public SimpleTransactionManagerUsingAutoProxy tranmanager()
    {
    	return new SimpleTransactionManagerUsingAutoProxy();
    }
	
}


