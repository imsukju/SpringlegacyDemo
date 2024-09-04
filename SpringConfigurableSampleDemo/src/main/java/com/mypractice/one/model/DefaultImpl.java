package com.mypractice.one.model;

public class DefaultImpl implements PracticeDeclareTracked{
	
	@Override
	public void hello(String hi)
	{
		System.out.println("This is defaultimpl" + hi);
	}

}
