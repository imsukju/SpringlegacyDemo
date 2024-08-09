package com.factory;

public class ClientService {
	private static ClientService clientService = new ClientService();
	private ClientService() {}

	public static ClientService createInstance() {
		return clientService;
	}
	
	public void atest()
	{
		System.out.println("aaa");
	}
}
