package bank.controller;

import bank.dto.AccountDTO;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Accounts {
    private final Collection<AccountDTO> accountList;

    public Accounts() {
        this.accountList = new ArrayList<>();
    }

    public Accounts(Collection<AccountDTO> accountList) {
        this.accountList = new ArrayList<>(accountList);
    }

    public Collection<AccountDTO> getAccountList() {
        return Collections.unmodifiableCollection(accountList);
    }

    public void addAccount(AccountDTO account) {
        this.accountList.add(account);
    }

    public void addAccounts(Collection<AccountDTO> accounts) {
        this.accountList.addAll(accounts);
    }
}
