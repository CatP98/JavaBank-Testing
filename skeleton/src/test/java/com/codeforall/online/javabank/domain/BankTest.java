package com.codeforall.online.javabank.domain;

import com.codeforall.online.javabank.domain.account.Account;
import com.codeforall.online.javabank.domain.account.AccountType;
import com.codeforall.online.javabank.exceptions.CustomerNotFoundException;
import com.codeforall.online.javabank.factories.AccountFactory;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(JUnitParamsRunner.class)
public class BankTest {

    //SUT
    private Bank bank;

    //DOCs
    private Map<Integer,Customer> customers;
    private AccountFactory accountFactory;

    @Before
    public void setup() {
        //bank creation
        this.bank = new Bank();

        //customer creation
        //Customer customer = mock(Customer.class);

        //list of customers creation, add a customer, set it to the bank
        this.customers = new HashMap<>();

        bank.setCustomers(customers);

        //accountFactory creation and set
        this.accountFactory = mock(AccountFactory.class);
        bank.setAccountFactory(accountFactory);
    }


    // ---------------------------------------------Testing getCustomersList()-------------------------------------------

    /*
    Test with an Empty Map: Verify that the method returns an empty list when the customers map is empty.
     */
    @Test
    public void testIfGetCustomersListReturnsEmptyListWhenMapIsEmpty() {
        //exercise  : Create a list from the values of the 'customers' map, which will return an empty list if the map is empty.
        List<Customer> customersList = bank.getCustomersList();

        // Note: Remember that 'customers.values()', from 'getCustomersList()' will return a collection,
        // that will be stored on an ArrayList (a type of List).so with the getCustomersList we are converting the Map of customers
        // (key-value pairs) into a List pf customers, in order to be easier to manipulate the data, since 'List' interface offers
        // an ordered list collection with methods for positional access and iteraction

        //verify
        assertTrue(customersList.isEmpty());
    }

    /*
    Test with Non-Empty Map: Verify that the method returns a list containing all the customers when the customers map is populated.
     */
    @Test
    public void testIfGetCustomersListReturnsCorrectListWhenMapIsNotEmpty(){
        // Set up
        Customer customer1 = mock(Customer.class);
        Customer customer2 = mock(Customer.class);
        Customer customer3 = mock(Customer.class);

        //Note: mocked customers may have the same default value for id, causing them to overwrite each other.
        // If multiple customers have the same ID, the map will only keep one of them, bc n a Map, each key must be unique.
        // When you put a new entry with a key that already exists, the new entry replaces the old one.

        when(customer1.getId()).thenReturn(1);
        when(customer2.getId()).thenReturn(2);
        when(customer3.getId()).thenReturn(3);

        bank.addCustomer(customer1);
        bank.addCustomer(customer2);
        bank.addCustomer(customer3);

        // Exercise
        List<Customer> customersList = bank.getCustomersList();

        // Verify
        assertEquals(3, customersList.size());
        assertTrue(customersList.contains(customer1));
        assertTrue(customersList.contains(customer2));
        assertTrue(customersList.contains(customer3));
    }

    /*
    Test for Independence: Verify that the returned list is a new ArrayList and not a reference to the internal map,
    ensuring that modifications to the returned list do not affect the original customers map.
     */
    @Test
    public void testGetCustomersListIsIndependentOfInternalMap(){
        //setup
        Customer customer1 = mock(Customer.class);
        Customer customer2 = mock(Customer.class);
        Customer customer3 = mock(Customer.class);

        when(customer1.getId()).thenReturn(1);
        when(customer2.getId()).thenReturn(2);
        when(customer3.getId()).thenReturn(3);

        bank.addCustomer(customer1);
        bank.addCustomer(customer2);
        bank.addCustomer(customer3);

        //exercise
        List<Customer> customerList1 = bank.getCustomersList();

        // Verify that the list is a separate instance from the internal map, having a differnt reference
        assertNotSame("The returned list should not be the same reference as the internal map",
                bank.getCustomers().values(), customerList1);

        // do changes on the list
        customerList1.remove(customer1);

        // Exercise again to get a fresh list
        List<Customer> customerList2 = bank.getCustomersList();

        //verify
        assertNotEquals(customerList1, customerList2);

        // Verify that the internal map is unaffected and customerList2 reflects the full list
        assertTrue("The internal map should still contain the removed customer.",
                bank.getCustomers().containsValue(customer1));

        assertEquals("The size of the list from getCustomersList should be 3.",
                3, customerList2.size());
    }

    // ---------------------------------------------Testing customerExists()----------------------------------------------
    @Test
    @Parameters({"1"})
    public void testCustomerExistsWhenCustomerExists(int index){
        //setUp
        Customer customer = mock(Customer.class);
        when(customer.getId()).thenReturn(1);
        customers.put(1,customer);

        assertTrue(bank.customerExists(1));
    }

    @Test
    public void testCustomerExistsWhenCustomerDoesNotExist(){
        // No need to mock a customer since we are testing non-existence
        // Verify
        assertFalse(bank.customerExists(1));
    }

    // ---------------------------------------------Testing getNextCustomerId()------------------------------------------
    /*
    public Integer getNextCustomerId() {
        return ++nextCustomerId;
     */

    @Test
    @Parameters({"1, 2", "2, 3", "3, 4"})
    public void testGetNextCustomerIdSequentially(int index, int expectedIndex){
        Customer customer = mock(Customer.class);
        when(customer.getId()).thenReturn(index);
        bank.addCustomer(customer);

        //assertEquals(expectedIndex, bank.getNextCustomerId());
    }

    @Test
    @Parameters({"2,6,8", "6,9,10"})
    public void testGetNextCustomerIdInitialValue(){}

    // ---------------------------------------------Testing addCustomer()----------------------------------------
    @Test
    public void testAddCustomer() throws CustomerNotFoundException {
        Customer customer = mock(Customer.class);
        when(customer.getId()).thenReturn(1);

        bank.addCustomer(customer);

        assertTrue(bank.getCustomersList().contains(customer));
        assertEquals(1, bank.getCustomersList().size());
        assertEquals(customer, bank.getCustomer(customer.getId()));
    }

    // ---------------------------------------------Testing getCustomer()----------------------------------------
    @Test
    @Parameters({"1","4","100","1987"})
    public void testGetCustomer(int index) throws CustomerNotFoundException {
        Customer customer = mock(Customer.class);
        when(customer.getId()).thenReturn(index);

        bank.addCustomer(customer);

        assertEquals(customer, bank.getCustomer(index));
    }

    @Test(expected = CustomerNotFoundException.class)
    @Parameters({"1", "2", "100"})
    public void testGetCustomerThrowsCustomerNotFoundException(int index) throws CustomerNotFoundException {
       bank.getCustomer(index);
    }

    /*
    //Another way, using assertThrows()
    @Test
    @Parameters({"100"})
    public void testGetCustomerThrowsCustomerNotFoundException(Integer id) {
        assertThrows(CustomerNotFoundException.class, () -> bank.getCustomer(id));
    }
    */

    // ---------------------------------------------Testing openAccount()------------------------------------------------

    /*
      public void openAccount(AccountType accountType, Integer customerId, double balance) throws CustomerNotFoundException {

        if(accountType.equals(AccountType.SAVINGS) && balance < 100) {
            return;
        }
        Account account = accountFactory.createAccount(accountType);
        account.debit(balance);
        account.setId(getNextAccountId());

        getCustomer(customerId).addAccount(account);
    }
     */

    @Test
    @Parameters ({"110.0, 1, AccountType.SAVINGS"})
    public void testOpenAccount() throws CustomerNotFoundException {
        //setUp

        //Exercise


        //verify

    }

    public void testOpenSavingsAccountWithoutMinBalance(double balance) throws CustomerNotFoundException{}

    public void testOpenSavingsAccountWithMinBalance(double balance) throws CustomerNotFoundException{}

    public void testOpenAccountThrowsCustomerNotFoundException(){}


}