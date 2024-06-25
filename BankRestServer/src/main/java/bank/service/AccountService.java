package bank.service;

import bank.adapter.AccountAdapter;
import bank.dao.IAccountDAO;
import bank.domain.Account;
import bank.domain.AccountChangeEvent;
import bank.domain.Customer;
import bank.dto.AccountDTO;
import bank.integration.jms.IJMSSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService implements IAccountService {

    private static final Logger logger = LoggerFactory.getLogger(AccountService.class);

    private final IAccountDAO accountDAO;
    private final ICurrencyConverter currencyConverter;
    private final IJMSSender jmsSender;
    private final BankStatementPrinter bankStatementPrinter;
    private final ApplicationEventPublisher publisher;

    @Autowired
    public AccountService(IAccountDAO accountDAO, ICurrencyConverter currencyConverter, 
                          IJMSSender jmsSender, BankStatementPrinter bankStatementPrinter,
                          ApplicationEventPublisher publisher) {
        this.accountDAO = accountDAO;
        this.currencyConverter = currencyConverter;
        this.jmsSender = jmsSender;
        this.bankStatementPrinter = bankStatementPrinter;
        this.publisher = publisher;
    }

    @Scheduled(cron = "*/10 * * * * *")
    public void printBankStatements() {
        bankStatementPrinter.printStatements(accountDAO.findAll());
    }

    @Override
    public AccountDTO createAccount(long accountNumber, String customerName) {
        Account account = new Account(accountNumber);
        Customer customer = new Customer(customerName);
        account.setCustomer(customer);
        accountDAO.save(account);
        logger.info("Created account with accountNumber= {} , customerName= {}", accountNumber, customerName);
        return AccountAdapter.toDto(account);
    }

    @Override
    public void deposit(long accountNumber, double amount) {
        Optional<Account> optionalAccount = accountDAO.findById(accountNumber);
        if (optionalAccount.isPresent()) {
            Account account = optionalAccount.get();
            account.deposit(amount);
            accountDAO.save(account);
            logger.info("Deposit with accountNumber= {} , amount= {}", accountNumber, amount);
            if (amount > 10000) {
                jmsSender.sendJMSMessage(String.format("Deposit of $ %.2f to account with accountNumber= %d", amount, accountNumber));
            }
            publisher.publishEvent(new AccountChangeEvent("deposit", accountNumber, amount));
        } else {
            logger.warn("Account with accountNumber= {} not found for deposit", accountNumber);
        }
    }

    @Override
    public AccountDTO getAccount(long accountNumber) {
        Optional<Account> optionalAccount = accountDAO.findById(accountNumber);
        return optionalAccount.map(AccountAdapter::toDto).orElse(null);
    }

    @Override
    public Collection<AccountDTO> getAllAccounts() {
        List<Account> accountList = accountDAO.findAll();
        return AccountAdapter.toDtoList(accountList);
    }

    @Override
    public void withdraw(long accountNumber, double amount) {
        Optional<Account> optionalAccount = accountDAO.findById(accountNumber);
        if (optionalAccount.isPresent()) {
            Account account = optionalAccount.get();
            account.withdraw(amount);
            accountDAO.save(account);
            logger.info("Withdraw with accountNumber= {} , amount= {}", accountNumber, amount);
            publisher.publishEvent(new AccountChangeEvent("withdraw", accountNumber, amount));
        } else {
            logger.warn("Account with accountNumber= {} not found for withdrawal", accountNumber);
        }
    }

    @Override
    public void depositEuros(long accountNumber, double amount) {
        Optional<Account> optionalAccount = accountDAO.findById(accountNumber);
        if (optionalAccount.isPresent()) {
            Account account = optionalAccount.get();
            double amountDollars = currencyConverter.euroToDollars(amount);
            account.deposit(amountDollars);
            accountDAO.save(account);
            logger.info("Deposit Euros with accountNumber= {} , amount= {}", accountNumber, amount);
            if (amountDollars > 10000) {
                jmsSender.sendJMSMessage(String.format("Deposit of $ %.2f to account with accountNumber= %d", amountDollars, accountNumber));
            }
            publisher.publishEvent(new AccountChangeEvent("deposit-euros", accountNumber, amount));
        } else {
            logger.warn("Account with accountNumber= {} not found for depositEuros", accountNumber);
        }
    }

    @Override
    public void withdrawEuros(long accountNumber, double amount) {
        Optional<Account> optionalAccount = accountDAO.findById(accountNumber);
        if (optionalAccount.isPresent()) {
            Account account = optionalAccount.get();
            double amountDollars = currencyConverter.euroToDollars(amount);
            account.withdraw(amountDollars);
            accountDAO.save(account);
            logger.info("Withdraw Euros with accountNumber= {} , amount= {}", accountNumber, amount);
            publisher.publishEvent(new AccountChangeEvent("withdraw-euros", accountNumber, amount));
        } else {
            logger.warn("Account with accountNumber= {} not found for withdrawEuros", accountNumber);
        }
    }

    @Override
    public void transferFunds(long fromAccountNumber, long toAccountNumber, double amount, String description) {
        Optional<Account> optionalFromAccount = accountDAO.findById(fromAccountNumber);
        Optional<Account> optionalToAccount = accountDAO.findById(toAccountNumber);

        if (optionalFromAccount.isPresent() && optionalToAccount.isPresent()) {
            Account fromAccount = optionalFromAccount.get();
            Account toAccount = optionalToAccount.get();
            fromAccount.transferFunds(toAccount, amount, description);
            accountDAO.save(fromAccount);
            accountDAO.save(toAccount);
            logger.info("Transfer Funds with fromAccountNumber= {} , toAccountNumber= {} , amount= {} , description= {}", fromAccountNumber, toAccountNumber, amount, description);
            if (amount > 10000) {
                jmsSender.sendJMSMessage(String.format("Transfer Funds of $ %.2f from account with accountNumber= %d to account with accountNumber= %d", amount, fromAccountNumber, toAccountNumber));
            }
            publisher.publishEvent(new AccountChangeEvent("transfer_from", fromAccountNumber, amount));
            publisher.publishEvent(new AccountChangeEvent("transfer_to", toAccountNumber, amount));
        } else {
            logger.warn("Accounts with fromAccountNumber= {} or toAccountNumber= {} not found for transfer", fromAccountNumber, toAccountNumber);
        }
    }
}
