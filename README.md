# JavaBank Unit Testing

## Overview

This repository contains the implementation of unit tests for the **JavaBank** backend system. The tests are designed to ensure that the JavaBank codebase functions correctly, covering various scenarios, including edge cases and error conditions.

## Project Structure

The project follows the standard Maven project layout:
/skeleton/src/test/java/com/codeforall/online/javabank


## Languages

- **Java**: The primary programming language used for development.

## Frameworks and Libraries

- **JUnit**: A testing framework used for writing and running tests.
- **Mockito**: A mocking framework used for creating mock objects in tests.
- **JUnitParams**: A parameterized testing library for JUnit, used to run the same test with different parameters.

## Tools

- **Git**: Version control system used for tracking changes.
- **Maven**: Build automation tool used for managing dependencies and building the project.
- **IDE**: An Integrated Development Environment (such as IntelliJ IDEA or Eclipse) for coding and debugging.

## Dependencies

The project uses the following dependencies:

```xml
<dependencies>
    <dependency>
        <groupId>junit</groupId>
        <artifactId>junit</artifactId>
        <version>4.13.1</version>
        <scope>test</scope>
    </dependency>

    <dependency>
        <groupId>org.mockito</groupId>
        <artifactId>mockito-core</artifactId>
        <version>2.21.0</version>
        <scope>test</scope>
    </dependency>

    <dependency>
        <groupId>junitparams</groupId>
        <artifactId>junit-params</artifactId>
        <version>1.1.1</version>
        <scope>test</scope>
    </dependency>
</dependencies>
```

## Running the Tests

To run the unit tests, use the following Maven command:

```bash
mvn test
```
## Tests Overview

### BankTest

- **`testIfGetCustomersListReturnsEmptyListWhenMapIsEmpty`**: Verifies that an empty map returns an empty list.
- **`testIfGetCustomersListReturnsCorrectListWhenMapIsNotEmpty`**: Checks if a non-empty map returns a list with all customers.
- **`testGetCustomersListIsIndependentOfInternalMap`**: Ensures that the returned list is independent of the internal map.
- **`testCustomerExistsWhenCustomerExists`**: Tests if a customer exists in the bank.
- **`testCustomerExistsWhenCustomerDoesNotExist`**: Verifies that a non-existent customer is not found.
- **`testGetNextCustomerIdSequentially`**: (Incomplete) Should test sequential customer ID retrieval.
- **`testAddCustomer`**: Tests the addition of a customer to the bank.
- **`testGetCustomer`**: Checks if a customer can be retrieved by ID.
- **`testGetCustomerThrowsCustomerNotFoundException`**: Verifies that an exception is thrown when a customer is not found.
- **`testOpenAccount`**: (Incomplete) Should test the opening of an account.
- **`testOpenSavingsAccountWithoutMinBalance`**: (Incomplete) Should test opening a savings account without the minimum balance.
- **`testOpenSavingsAccountWithMinBalance`**: (Incomplete) Should test opening a savings account with the minimum balance.
- **`testOpenAccountThrowsCustomerNotFoundException`**: (Incomplete) Should test that an exception is thrown when a customer is not found while opening an account.

### CustomerTest

- **`testIfInitialBalanceIs0`**: Verifies that the initial balance of a customer is zero.
- **`testGetTotalBalanceWithOneAccount`**: Checks the total balance with one account.
- **`testGetTotalBalanceWithMultipleAccounts`**: Verifies the total balance with multiple accounts.
- **`testIfAddsAccountToList`**: Tests if an account is added to the customer's account list.
- **`testAddMultipleAccounts`**: Ensures that multiple accounts are added correctly.
- **`testAddNullAccount`**: Tests the behavior when adding a null account.
- **`testIfListCreatedIsEmpty`**: Checks that a newly created account list is empty.
- **`testGettersAndSetters`**: Verifies the getters and setters for customer properties.

### AccountFactoryTest

- **`shouldCreateCheckingAccountWhenAccountTypeIsCHECKING`**: Tests if a checking account is created for the CHECKING type.
- **`shouldCreateSavingsAccountWhenAccountTypeIsSAVINGS`**: Verifies that a savings account is created for the SAVINGS type.
- **`shouldThrowIllegalArgumentException`**: Tests that an exception is thrown for unsupported account types.
- **`shouldThrowIllegalArgumentExceptionWithAssertThrows`**: (Alternative approach) Checks for an exception using `assertThrows`.

## Contribution

1. **Clone the Repository**:

    ```bash
    git clone <repository-url>
    ```

2. **Create a New Branch**:

    ```bash
    git checkout -b your-branch-name
    ```

3. **Make Changes**: Implement and test new features or fixes.

4. **Stage and Commit**:

    ```bash
    git add .
    git commit -m "Your descriptive commit message"
    ```

5. **Push Your Branch**:

    ```bash
    git push origin your-branch-name
    ```

6. **Create a Pull Request**: Open a pull request on GitHub to merge your changes.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.


