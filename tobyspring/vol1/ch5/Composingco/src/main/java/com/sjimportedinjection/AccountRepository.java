package com.sjimportedinjection;

public interface AccountRepository {
    void transfer(double amount, String fromAccountId, String toAccountId);
}
