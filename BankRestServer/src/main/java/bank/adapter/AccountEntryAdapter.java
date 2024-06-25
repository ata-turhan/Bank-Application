package bank.adapter;

import bank.domain.AccountEntry;
import bank.dto.AccountEntryDTO;

public class AccountEntryAdapter {

    private AccountEntryAdapter() {
        // Private constructor to prevent instantiation
    }

    public static AccountEntry toEntity(AccountEntryDTO accountEntryDto) {
        if (accountEntryDto == null) {
            return null;
        }

        AccountEntry accountEntry = new AccountEntry();
        accountEntry.setDate(accountEntryDto.getDate());
        accountEntry.setAmount(accountEntryDto.getAmount());
        accountEntry.setDescription(accountEntryDto.getDescription());
        accountEntry.setFromAccountNumber(accountEntryDto.getFromAccountNumber());
        accountEntry.setFromPersonName(accountEntryDto.getFromPersonName());
        return accountEntry;
    }

    public static AccountEntryDTO toDto(AccountEntry accountEntry) {
        if (accountEntry == null) {
            return null;
        }

        AccountEntryDTO accountEntryDto = new AccountEntryDTO();
        accountEntryDto.setDate(accountEntry.getDate());
        accountEntryDto.setAmount(accountEntry.getAmount());
        accountEntryDto.setDescription(accountEntry.getDescription());
        accountEntryDto.setFromAccountNumber(accountEntry.getFromAccountNumber());
        accountEntryDto.setFromPersonName(accountEntry.getFromPersonName());
        return accountEntryDto;
    }
}
