package bank.service;

import bank.dto.AccountDTO;

import java.util.Collection;

public interface IAccountService {
    AccountDTO createAccount(long accountNumber, String customerName);
    AccountDTO getAccount(long accountNumber);
    Collection<AccountDTO> getAllAccounts();
    void deposit(long accountNumber, double amount);
    void withdraw(long accountNumber, double amount);
    void depositEuros(long accountNumber, double amount);
    void withdrawEuros(long accountNumber, double amount);
    void transferFunds(long fromAccountNumber, long toAccountNumber, double amount, String description);
}
