package com.mypractice.two.bank;

import java.util.HashMap;

public interface Banks {
	public void depositMoney(HashMap<String, Integer> info, int a);
	public void receiveDeposit(HashMap<String, Integer> info, int a);

}
