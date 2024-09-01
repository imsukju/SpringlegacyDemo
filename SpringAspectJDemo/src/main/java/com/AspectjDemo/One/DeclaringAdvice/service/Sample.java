package com.AspectjDemo.One.DeclaringAdvice.service;

import java.util.Collection;

public interface Sample<T> 
{
	void sampleGenericMethod(T param);
	void sampleGenericCollectionMethod(Collection<T> param);
}
