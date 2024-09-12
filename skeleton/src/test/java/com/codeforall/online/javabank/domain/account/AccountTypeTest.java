package com.codeforall.online.javabank.domain.account;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AccountTypeTest {
    //SUT
    AccountType accountType;
    //setUp -> Enum Type cannot be instantiated

    @Test
    public void testEnumValues(){
        AccountType savingsAccount = AccountType.SAVINGS;
        AccountType checkingAccount = AccountType.CHECKING;

        assertEquals(AccountType.CHECKING, checkingAccount);
        assertEquals(AccountType.SAVINGS, savingsAccount);
    }
}