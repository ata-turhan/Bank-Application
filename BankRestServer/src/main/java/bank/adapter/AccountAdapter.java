package bank.adapter;

import bank.domain.Account;
import bank.dto.AccountDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AccountAdapter {

    private AccountAdapter() {
        // Private constructor to prevent instantiation
    }

    public static Account toEntity(AccountDTO accountDto) {
        if (accountDto == null) {
            return null;
        }

        Account account = new Account();
        account.setAccountNumber(accountDto.getAccountNumber());
        account.setCustomer(CustomerAdapter.toEntity(accountDto.getCustomer()));
        accountDto.getEntryList().forEach(entryDto -> account.addEntry(AccountEntryAdapter.toEntity(entryDto)));

        return account;
    }

    public static AccountDTO toDto(Account account) {
        if (account == null) {
            return null;
        }

        AccountDTO accountDto = new AccountDTO();
        accountDto.setAccountNumber(account.getAccountNumber());
        accountDto.setBalance(account.getBalance());
        accountDto.setCustomer(CustomerAdapter.toDto(account.getCustomer()));
        accountDto.setEntryList(account.getEntryList().stream()
                                      .map(AccountEntryAdapter::toDto)
                                      .collect(Collectors.toList()));

        return accountDto;
    }

    public static List<AccountDTO> toDtoList(List<Account> accountList) {
        if (accountList == null) {
            return new ArrayList<>();
        }

        return accountList.stream()
                          .map(AccountAdapter::toDto)
                          .collect(Collectors.toList());
    }
}
