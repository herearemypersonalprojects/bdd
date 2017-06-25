package com.lequocanh.account;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by quocanh on 25/06/2017.
 * This class represents a simple account.
 * It doesn't handle different currencies, all money is supposed to be of standard currency EUR.
 */
public interface Account {
    public static DateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yy");

    /**
     * Adds money to this account.
     * @param addedAmount - the money to add
     */
    void add(Double addedAmount);

    /**
     * Withdraws money from the account.
     * @param withdrawnAmount - the money to withdraw
     * @param rule - the AccountRule that defines which balance is allowed for this account
     * @return the remaining account balance
     * @throws IllegalBalanceException if the withdrawal leaves the account with a forbidden balance
     */
    Double withdrawAndReportBalance(Double withdrawnAmount, AccountRule rule) throws IllegalBalanceException;

    /**
     * Gets the current account balance.
     * @return the account's balance
     */
    Double getBalance();

    /**
     * Get the history (operation, date, amount, balance) of my operations
     */
    String getHistory();
}