package com.codeforall.online.javabank.domain;

import com.codeforall.online.javabank.domain.account.Account;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

@RunWith(JUnitParamsRunner.class)
public class CustomerTest {

    //SUD
    private Customer customer;

    //SetUp
    @Before
    public void stetUp() {
        this.customer = new Customer();
    }

    @Test
    @Parameters({"0"})
    public void testIfInitialBalanceIs0(int expectedBalance){
        assertEquals(expectedBalance, customer.getTotalBalance(), 0.0);
    }

    @Test
    @Parameters({"10.0", "20.0", "0.0", "100.0"})
    public void testGetTotalBalanceWithOneAccount(double balance){
        //setup
        Account account = mock(Account.class);
        when(account.getBalance()).thenReturn(balance);

        //exercise
        customer.addAccount(account);

        //verify
        assertEquals(balance, customer.getTotalBalance(),  0.0);
    }

    @Test
    @Parameters({"10.0"})
    public void testGetTotalBalanceWithMultipleAccounts(double balance){
        //setup
        Account account1 = mock(Account.class);
        Account account2 = mock(Account.class);

        when(account1.getBalance()).thenReturn(balance);
        when(account2.getBalance()).thenReturn(balance);

        //exercise
        customer.addAccount(account1);
        customer.addAccount(account2);

        //verify
        assertEquals(balance + balance, customer.getTotalBalance(), 0.0);
    }

    @Test
    public void testIfAddsAccountToList(){
        //setUp
        Account account = mock(Account.class);

        //Exercise
        customer.addAccount(account);

        //verify
        assertTrue(customer.getAccounts().contains(account));
        assertEquals(1, customer.getAccounts().size());
    }

    @Test
    @Parameters({"0, 1, 2, 3"})
    public void testAddMultipleAccounts(int index1, int index2, int index3, int expectedSize){
        //setUp
        Account account1 = mock(Account.class);
        Account account2 = mock(Account.class);
        Account account3 = mock(Account.class);

        //Exercise
        customer.addAccount(account1);
        customer.addAccount(account2);
        customer.addAccount(account3);

        //verify
        assertEquals(expectedSize, customer.getAccounts().size());
        assertEquals(account1, customer.getAccounts().get(index1));
        assertEquals(account2, customer.getAccounts().get(index2));
        assertEquals(account3, customer.getAccounts().get(index3));
    }

    @Test(expected = NullPointerException.class)
    public void testAddNullAccount() {
        //setUp
        Account account = null;
        //exercise
        customer.addAccount(account);
    }

    @Test
    public void testIfListCreatedIsEmpty() {
        assertEquals(0, customer.getAccounts().size());
    }

    @Test
    public void testGettersAndSetters(){
        //setUp
        customer.setId(1);
        customer.setFirstName("Leonardo");
        customer.setLastName("DiCaprio");
        customer.setEmail("leonardo_dicaprio@hotmail.com");
        customer.setPhone("911665171");

        //verify
        assertEquals((Integer) 1, customer.getId());
        assertEquals("Leonardo", customer.getFirstName());
        assertEquals("DiCaprio", customer.getLastName());
        assertEquals("leonardo_dicaprio@hotmail.com", customer.getEmail());
        assertEquals("911665171", customer.getPhone());
    }
}