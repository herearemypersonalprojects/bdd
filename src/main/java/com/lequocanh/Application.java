package com.lequocanh;

import com.lequocanh.account.Account;
import com.lequocanh.account.AccountRule;
import com.lequocanh.account.IllegalBalanceException;
import com.lequocanh.account.implementation.CustomerAccount;
import com.lequocanh.account.implementation.CustomerAccountRule;

import java.util.Calendar;

/**
 * Created by quocanh on 25/06/2017.
 */
public class Application {
    public static void main(String[] args) {
        Account account = new CustomerAccount();
        AccountRule rule = new CustomerAccountRule();
        Calendar calendar = Calendar.getInstance();
        account.add(10d);
        account.add(6d);
        try {
            account.withdrawAndReportBalance(9d, rule);
            System.out.println(account.getHistory());
        } catch (IllegalBalanceException e) {
            System.out.println(e);
        }
    }
}
