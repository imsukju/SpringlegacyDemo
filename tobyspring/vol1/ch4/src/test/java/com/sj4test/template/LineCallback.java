package com.sj4test.template;

public interface LineCallback<T> {
	T doSomethingWithLine(String line, T value);
}