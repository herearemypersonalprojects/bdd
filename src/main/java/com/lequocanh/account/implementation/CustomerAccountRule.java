package com.lequocanh.account.implementation;

import com.lequocanh.account.*;

/**
 * Created by quocanh on 25/06/2017.
 * The implementation of the account rule class.
 */
public class CustomerAccountRule implements AccountRule {

    /**
     * Checks if the resulting account balance after a withdrawal is not negative.
     * @param resultingAccountBalance : the balance to be checked
     * @return true if the balance is not negative, false otherwise
     */
    public boolean withdrawPermitted(Double resultingAccountBalance) {
        checkNullValue(resultingAccountBalance, "The account's balance as input is null");
        return resultingAccountBalance >= 0;
    }

    /**
     * Tests in case a null value throws an exception.
     * @param input : the value to be checked.
     * @param msg : the message to inform.
     */
    private void checkNullValue(Double input, String msg) {
        if (input == null) {
            throw new IllegalArgumentException(msg);
        }
    }
}
