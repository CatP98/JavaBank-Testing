package com.codeforall.online.javabank.factories;

import com.codeforall.online.javabank.domain.account.Account;
import com.codeforall.online.javabank.domain.account.AccountType;
import com.codeforall.online.javabank.domain.account.CheckingAccount;
import com.codeforall.online.javabank.domain.account.SavingsAccount;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AccountFactoryTest{

    //SUT
    private AccountFactory accountFactory;

    //setUp -- will run everytime, begore a new test starts (@Before)
    @Before
    public void setUp() {
        this.accountFactory = new AccountFactory();
    }

    // @Test will inform the JUnit test runner that this is a test method that should be testing if it's passing or not.
    // the testing methods don't need to return anything, because it's the framework thar will tell us if the test is passing or not

    /*
    Test that a CheckingAccount is being created when the account type is CHECKING.
     */

    @Test
    public void shouldCreateCheckingAccountWhenAccountTypeIsCHECKING(){
        //Exercise
        Account newAccount = accountFactory.createAccount(AccountType.CHECKING);

        //Verify
        assertNotNull(newAccount);
        assertTrue(newAccount instanceof CheckingAccount);
    }

    /*
   Test that a SavingsAccount is being created when the account type is SAVINGS.
    */
    @Test
    public void shouldCreateSavingsAccountWhenAccountTypeIsSAVINGS(){
        //Exercise
        Account newAccount = accountFactory.createAccount(AccountType.SAVINGS);

        //Verify
        assertNotNull(newAccount);
        assertTrue(newAccount instanceof SavingsAccount);
    }

    //----------------------------Didn't pass the tests------------------------------------------

    /*
   Test that an IllegalArgumentException is thrown for unsupported account types.
    */
    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentException(){
        accountFactory.createAccount(null);
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWithAssertThrows() {
        assertThrows(IllegalArgumentException.class, () -> accountFactory.createAccount(null));
    }


/*
we don't need to assign the result of accountFactory.createAccount(AccountType.PENSION) to a variable (like newAccount)
when using assertThrows, because the primary purpose of this test is to check for an exception rather than to inspect
the returned object. The focus is on whether the method call itself triggers an IllegalArgumentException.
 */
}