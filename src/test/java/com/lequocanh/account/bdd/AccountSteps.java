package com.lequocanh.account.bdd;

import com.lequocanh.account.Account;
import com.lequocanh.account.AccountRule;
import com.lequocanh.account.IllegalBalanceException;
import com.lequocanh.account.implementation.CustomerAccount;
import com.lequocanh.account.implementation.CustomerAccountRule;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.junit.Assert;

/**
 * Created by quocanh on 25/06/2017.
 */
public class AccountSteps {
    private Account account;
    private AccountRule rule = new CustomerAccountRule();

    @Given("a bank account")
    public void givenABankAccount() {
        account = new CustomerAccount();
    }

    @When("make a deposit of $amount in the account")
    public void makeDesposit(double amount) {
        account.add(amount);
    }

    @When("make a withdrawal of $amount from the account")
    public void makeWithdrawal(double amount) throws IllegalBalanceException {
        account.withdrawAndReportBalance(amount, rule);
    }

    @Then("would see $balance in the account")
    public void verifyBalance(Double balance){
        Assert.assertEquals(balance, account.getBalance());
    }

    @Then("would see $history")
    public void verifyHistory(String history) {
        Assert.assertTrue(history.equals(account.getHistory().trim()));
    }
}
