package com.lequocanh.account.implementation;

import com.lequocanh.account.*;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

/**
 * Created by quocanh on 25/06/2017.
 * The implementation of the customer account class.
 */
public class CustomerAccount implements Account {
    /** The maximal amount permitted for each withdrawal. */
    public static final Double MAX_WITHDRAWAL_AMOUNT_PERMITTED = 7000d;

    List<Operation> operationList = new ArrayList<Operation>();

    /** The actual money in the account. */
    private Double amount;

    /**
     * Initialize the account.
     */
    public CustomerAccount() {
        amount = 0.0d;
    }

    /**
     * Adds money to this account and save to history.
     * @param addedAmount : the money to be added.
     */
    public void add(Double addedAmount) {
        checkNullAmount(addedAmount);
        checkNegativeAmount(addedAmount);
        amount += addedAmount;

        operationList.add(new Operation(OperationType.DEPOSIT, Calendar.getInstance().getTime(), addedAmount, amount));
    }

    /**
     * Gets the current account balance.
     * @return the account's balance
     */
    public Double getBalance() {
        return amount;
    }

    /**
     * Get the history (operation, date, amount, balance) of my operations
     *
     */
    public String getHistory() {
        StringBuilder history = new StringBuilder();
        operationList.forEach(operation -> {
            history.append(operation.getType() + ":" + DATE_FORMAT.format(operation.getDate()) + ":" + operation.getAmount() + ":" + operation.getBalance());
            history.append(System.getProperty("line.separator"));
        });
        return history.toString();
    }


    /**
     * Withdrew an amount from the account and report the rest.
     * @param withdrawnAmount : the amount to be drawn
     * @param rule : checks if the operation is permitted
     * @throws IllegalBalanceException if the withdrawal leaves the account with a forbidden balance
     * @return the report balance after withdrawing an amount.
     */
    public Double withdrawAndReportBalance(Double withdrawnAmount, AccountRule rule)
            throws IllegalBalanceException {
        checkNullRule(rule);
        checkNullAmount(withdrawnAmount);
        checkNegativeAmount(withdrawnAmount);
        checkExceededAmount(withdrawnAmount);

        Double reportBalance = amount - withdrawnAmount;
        checkBalanceAmount(reportBalance, rule);
        amount = reportBalance;

        operationList.add(new Operation(OperationType.WITHDRAWAL, Calendar.getInstance().getTime(), withdrawnAmount, amount));

        return getBalance();
    }

    /**
     * An amount to be added or to be withdrawn throws an exception.
     * @param checkAmount : the amount to be checked
     * throw IllegalArgumentException if the amount is null
     */
    private void checkNegativeAmount(Double checkAmount) {
        if (checkAmount < 0) {
            throw new IllegalArgumentException("Amount cannot be negative");
        }
    }

    /**
     * An null amount to be added or to be withdrawn throws an exception.
     * @param checkAmount : the amount to be checked.
     * throw IllegalArgumentException if the amount is null
     */
    private void checkNullAmount(Double checkAmount) {
        if (checkAmount == null) {
            throw new IllegalArgumentException("Amount cannot be null");
        }
    }

    /**
     * A withdrawal exceeds a permitted amount once throws an exception.
     * @param checkAmount : amount to be checked
     */
    private void checkExceededAmount(Double checkAmount) {
        if (checkAmount > CustomerAccount.MAX_WITHDRAWAL_AMOUNT_PERMITTED) {
            throw new IllegalArgumentException("The withdrawal exceeds a permitted amount");
        }
    }

    /**
     * An amount to be withdrawn violates the rule throws an exception.
     * @param checkAmount : the amount to be checked
     * @param rule : the account's rule
     * @throws IllegalBalanceException if the amount is not permitted
     */
    private void checkBalanceAmount(Double checkAmount, AccountRule rule) throws IllegalBalanceException {
        if (!rule.withdrawPermitted(checkAmount)) {
            throw new IllegalBalanceException(checkAmount);
        }
    }

    /**
     * An account's rule to be null throws an exception.
     * @param rule : the account's rule to be checked.
     */
    private void checkNullRule(AccountRule rule) {
        if (rule == null) {
            throw new IllegalArgumentException("The account's rule is null");
        }
    }
}