package com.sj3_3.lamdatest;

public class LamdaTest1 {
	public static void main(String...arg)
	{
		LamdaTest1 a = new LamdaTest1();
		a.testim1();
	}
	
	public Interface1 testim()
	{
		int x = 3 ;
		int y = 4 ; 
		Interface1 i = (a,b)-> a = x+y;
		
		i.test(x, y);
		System.out.println(x);
		
		return i;
	}
	
	public void testim1()
	{
		int x = 2;
		this.testim().test(x, 6);
		
		System.out.println(x);
	}
}
