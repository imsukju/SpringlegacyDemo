package com.factoryint;

public interface ClientService {
    void doSomething();
}

class ClientServiceImpl implements ClientService {
    @Override
    public void doSomething() {
        System.out.println("ClientService is doing something");
    }
}