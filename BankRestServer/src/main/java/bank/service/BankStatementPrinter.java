package bank.service;

import bank.domain.Account;
import bank.domain.AccountEntry;
import bank.domain.Customer;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class BankStatementPrinter {

    public void printStatements(Collection<Account> accounts) {
        accounts.forEach(this::printStatement);
        System.out.println("-----------------------------------------------------------------");
    }

    private void printStatement(Account account) {
        Customer customer = account.getCustomer();
        System.out.println("Statement for Account: " + account.getAccountNumber());
        System.out.println("Account Holder: " + customer.getName());
        System.out.println(
                "-Date-------------------------" + "-Description------------------" + "-Amount-------------");
        account.getEntryList().forEach(this::printEntry);
        System.out.println("----------------------------------------" + "----------------------------------------");
        System.out.printf("%30s%30s%20.2f\n\n", "", "Current Balance:", account.getBalance());
    }

    private void printEntry(AccountEntry entry) {
        System.out.printf("%30s%30s%20.2f\n", entry.getDate().toString(), entry.getDescription(), entry.getAmount());
    }
}
