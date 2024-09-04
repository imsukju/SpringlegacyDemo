package com.configurableOne.service;

import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;


@Service
public class StubEntilementCallculationService implements EntitlementCalculationService{


	@Override
	public void Entitlement() {
		// TODO Auto-generated method stub
		try
		{
			Thread.sleep(1000);
			System.out.println("hello");
            System.out.println("Calcuating entilement");
		}
		catch(InterruptedException ex)
		{
			ex.getMessage();
			System.out.println("-StubEntitlementCalculationService::");
		}
	}
}
