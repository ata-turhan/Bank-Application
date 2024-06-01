package bank.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import bank.dto.AccountDTO;
import bank.service.AccountService;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping
    public ResponseEntity<Accounts> getAllAccounts() {
        Collection<AccountDTO> accountList = accountService.getAllAccounts();
        Accounts accounts = new Accounts(accountList);
        return new ResponseEntity<>(accounts, HttpStatus.OK);
    }

    @GetMapping("/{accountNumber}")
    public ResponseEntity<AccountDTO> getAccount(@PathVariable("accountNumber") long accountNumber) {
        AccountDTO accountDto = accountService.getAccount(accountNumber);
        return new ResponseEntity<>(accountDto, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<AccountDTO> createAccount(@RequestParam(value = "accountNumber") long accountNumber,
                                                    @RequestParam(value = "customerName") String customerName) {
        AccountDTO accountDto = accountService.createAccount(accountNumber, customerName);
        return new ResponseEntity<>(accountDto, HttpStatus.OK);
    }

    @PostMapping("/operation")
    public ResponseEntity<AccountDTO> accountOperation(@RequestParam(value = "accountNumber") long accountNumber,
                                                       @RequestParam(value = "amount") double amount,
                                                       @RequestParam(value = "operation") String operation,
                                                       @RequestParam(value = "toAccountNumber", required = false) Long toAccountNumber,
                                                       @RequestParam(value = "description", required = false) String description) {
        switch (operation) {
            case "deposit":
                accountService.deposit(accountNumber, amount);
                break;
            case "depositEuros":
                accountService.depositEuros(accountNumber, amount);
                break;
            case "withdraw":
                accountService.withdraw(accountNumber, amount);
                break;
            case "withdrawEuros":
                accountService.withdrawEuros(accountNumber, amount);
                break;
            case "transferFunds":
                accountService.transferFunds(accountNumber, toAccountNumber, amount, description);
                break;
            default:
                throw new IllegalArgumentException("Invalid operation: " + operation);
        }
        return new ResponseEntity<>(accountService.getAccount(accountNumber), HttpStatus.OK);
    }
}
