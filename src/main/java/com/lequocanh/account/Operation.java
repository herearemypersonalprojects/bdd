package com.lequocanh.account;

import java.util.Date;

/**
 * Created by quocanh on 25/06/2017.
 */
public class Operation {
    OperationType type;
    Date date;
    Double amount;
    Double balance;

    public Operation(OperationType type, Date date, Double amount, Double balance) {
        this.type = type;
        this.date = date;
        this.amount = amount;
        this.balance = balance;
    }

    public OperationType getType() {
        return type;
    }

    public void setType(OperationType type) {
        this.type = type;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }
}
