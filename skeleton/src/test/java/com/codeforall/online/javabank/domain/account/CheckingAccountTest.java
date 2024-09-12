package com.codeforall.online.javabank.domain.account;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CheckingAccountTest {
    //SUT
    CheckingAccount checkingAccount = new CheckingAccount();

    //setUp
    @Before
    public void setUp(){
        this.checkingAccount = new CheckingAccount();
    }

    @Test
    public void getAccountTypeReturnsChecking(){
        assertEquals(AccountType.CHECKING, checkingAccount.getAccountType());
    }
}