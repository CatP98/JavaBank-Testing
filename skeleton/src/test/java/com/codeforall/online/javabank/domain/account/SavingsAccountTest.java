package com.codeforall.online.javabank.domain.account;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(JUnitParamsRunner.class)
public class SavingsAccountTest {
    //SUT
    public SavingsAccount savingsAccount;

    //Setup
    @Before
    public void setUp(){
        this.savingsAccount = new SavingsAccount();
    }


    @Test
    public void testGetAccountTypeReturnsChecking(){
        //setUp, bc it's an enum, no need to mock

        //exrcise
       AccountType accountType = savingsAccount.getAccountType();
        //verify
        assertEquals(accountType,AccountType.SAVINGS);
    }

    @Test
    @Parameters({"100.0, 220.0", "150.0, 300.0"})
    public void testCanDebitAllowsWhenSufficientBalance(double amount, double balance){
        //setup
        savingsAccount.credit(balance);
        //exercise & verify
        assertTrue(savingsAccount.canDebit(amount));
    }

    @Test
    @Parameters({"110.0, 100.0", "300.0, 200.0"})
    public void testCanDebitDisallowsWhenUnsufficientBalance(double amount, double balance){
        //setup
        savingsAccount.credit(balance);
        //exercise & verify
        assertTrue(savingsAccount.canDebit(amount));
    }

    @Test
    @Parameters({"-110.0, 100.0", "-300.0, 200.0"})
    public void testCanDebitDisallowsWhenNegativeAmount(double amount, double balance){
        //setup
        savingsAccount.credit(balance);
        //exercise & verify
        assertTrue(savingsAccount.canDebit(amount));
    }
    @Test
    public void testWithdraw() {
        //setup
        savingsAccount.credit(100.0);
        //exercise & verify
        assertFalse(savingsAccount.canWithdraw());
    }
}