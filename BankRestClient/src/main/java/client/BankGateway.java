package client;

import java.util.Collection;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class BankGateway {

    private final RestTemplate restTemplate = new RestTemplate();
    private final String serverUrl = "http://localhost:8080/accounts";

    public AccountDTO getAccount(long accountNumber) {
        return restTemplate.getForObject(serverUrl + "/{accountNumber}", AccountDTO.class, accountNumber);
    }

    public Collection<AccountDTO> getAllAccounts() {
        Accounts accounts = restTemplate.getForObject(serverUrl, Accounts.class);
        return accounts.getAccountList();
    }

    public AccountDTO createAccount(long accountNumber, String customerName) {
        restTemplate.postForLocation(serverUrl + "/create?accountNumber={accountNumber}&customerName={customerName}", null, accountNumber, customerName);
        return getAccount(accountNumber);
    }

    public void deposit(long accountNumber, double amount) {
        restTemplate.postForLocation(serverUrl + "/operation?accountNumber={accountNumber}&amount={amount}&operation=deposit", null, accountNumber, amount);
    }

    public void withdraw(long accountNumber, double amount) {
        AccountDTO account = getAccount(accountNumber);
        if (account.getBalance() < amount) {
            throw new InsufficientFundsException("Insufficient funds for withdrawal");
        }
        restTemplate.postForLocation(serverUrl + "/operation?accountNumber={accountNumber}&amount={amount}&operation=withdraw", null, accountNumber, amount);
    }

    public void depositEuros(long accountNumber, double amount) {
        restTemplate.postForLocation(serverUrl + "/operation?accountNumber={accountNumber}&amount={amount}&operation=depositEuros", null, accountNumber, amount);
    }

    public void withdrawEuros(long accountNumber, double amount) {
        AccountDTO account = getAccount(accountNumber);
        if (account.getBalance() < amount) {
            throw new InsufficientFundsException("Insufficient funds for withdrawal");
        }
        restTemplate.postForLocation(serverUrl + "/operation?accountNumber={accountNumber}&amount={amount}&operation=withdrawEuros", null, accountNumber, amount);
    }

    public void transferFunds(long fromAccountNumber, long toAccountNumber, double amount, String description) {
        AccountDTO account = getAccount(fromAccountNumber);
        if (account.getBalance() < amount) {
            throw new InsufficientFundsException("Insufficient funds for transfer");
        }
        restTemplate.postForLocation(serverUrl + "/operation?accountNumber={accountNumber}&amount={amount}&operation=transferFunds&toAccountNumber={toAccountNumber}&description={description}", null, fromAccountNumber, amount, toAccountNumber, description);
    }
}
