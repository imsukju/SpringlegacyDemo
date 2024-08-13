package com.stvalue1.SpringLookupInjection;

public interface Command {
    void setState(Object state);
    Object execute();
}