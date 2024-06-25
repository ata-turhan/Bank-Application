package bank.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

public class AccountDTO {

    private long accountNumber;
    private double balance;
    private Collection<AccountEntryDTO> entryList = new ArrayList<>();
    private CustomerDTO customer;

    public AccountDTO() {
        // Default constructor for serialization/deserialization
    }

    public AccountDTO(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Collection<AccountEntryDTO> getEntryList() {
        return new ArrayList<>(entryList);
    }

    public void setEntryList(Collection<AccountEntryDTO> entryList) {
        this.entryList = new ArrayList<>(entryList);
    }

    public CustomerDTO getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDTO customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return String.format("AccountDTO{accountNumber=%d, balance=%.2f, customer=%s, entryList=%s}", 
                              accountNumber, balance, customer, entryList);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AccountDTO)) return false;
        AccountDTO that = (AccountDTO) o;
        return accountNumber == that.accountNumber && 
               Double.compare(that.balance, balance) == 0 &&
               Objects.equals(entryList, that.entryList) &&
               Objects.equals(customer, that.customer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountNumber, balance, entryList, customer);
    }
}
