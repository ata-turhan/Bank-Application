import bank.domain.Account;
import bank.domain.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.closeTo;

public class AccountTest {
    private Account toAccount;
    private Account fromAccount;

    @BeforeEach
    public void setup() {
        Customer customer1 = new Customer("Alice Johnson");
        toAccount = new Account();
        toAccount.setCustomer(customer1);

        Customer customer2 = new Customer("Bob Smith");
        fromAccount = new Account();
        fromAccount.setCustomer(customer2);
    }

    @Test
    public void testCreateAccount() {
        assertThat(toAccount.getBalance(), closeTo(0.0, 0.01));
    }

    @Test
    public void testDepositZero() {
        toAccount.deposit(0.0);
        assertThat(toAccount.getBalance(), closeTo(0.0, 0.01));
    }

    @Test
    public void testDeposit() {
        toAccount.deposit(200.0);
        assertThat(toAccount.getBalance(), closeTo(200.0, 0.01));
    }

    @Test
    public void testWithdrawZero() {
        toAccount.withdraw(0.0);
        assertThat(toAccount.getBalance(), closeTo(0.0, 0.01));
    }

    @Test
    public void testWithdraw() {
        toAccount.withdraw(50.0);
        assertThat(toAccount.getBalance(), closeTo(-50.0, 0.01));
    }

    @Test
    public void testTransferFundsZero() {
        fromAccount.transferFunds(toAccount, 0.0, "for groceries");
        assertThat(fromAccount.getBalance(), closeTo(0.0, 0.01));
        assertThat(toAccount.getBalance(), closeTo(0.0, 0.01));
    }

    @Test
    public void testTransferFunds() {
        fromAccount.transferFunds(toAccount, 150.0, "for rent");
        assertThat(fromAccount.getBalance(), closeTo(-150.0, 0.01));
        assertThat(toAccount.getBalance(), closeTo(150.0, 0.01));
    }
}
