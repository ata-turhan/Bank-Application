package bank.dto;

import java.util.Date;
import java.util.Objects;

public class AccountEntryDTO {

    private Date date;
    private double amount;
    private String description;
    private String fromAccountNumber;
    private String fromPersonName;

    public AccountEntryDTO() {
        // Default constructor for serialization/deserialization
    }

    public AccountEntryDTO(Date date, double amount, String description, 
                           String fromAccountNumber, String fromPersonName) {
        this.date = date;
        this.amount = amount;
        this.description = description;
        this.fromAccountNumber = fromAccountNumber;
        this.fromPersonName = fromPersonName;
    }

    public Date getDate() {
        return new Date(date.getTime());
    }

    public void setDate(Date date) {
        this.date = new Date(date.getTime());
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFromAccountNumber() {
        return fromAccountNumber;
    }

    public void setFromAccountNumber(String fromAccountNumber) {
        this.fromAccountNumber = fromAccountNumber;
    }

    public String getFromPersonName() {
        return fromPersonName;
    }

    public void setFromPersonName(String fromPersonName) {
        this.fromPersonName = fromPersonName;
    }

    @Override
    public String toString() {
        return String.format("AccountEntryDTO{date=%s, amount=%.2f, description='%s', fromAccountNumber='%s', fromPersonName='%s'}", 
                              date, amount, description, fromAccountNumber, fromPersonName);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AccountEntryDTO)) return false;
        AccountEntryDTO that = (AccountEntryDTO) o;
        return Double.compare(that.amount, amount) == 0 &&
               Objects.equals(date, that.date) &&
               Objects.equals(description, that.description) &&
               Objects.equals(fromAccountNumber, that.fromAccountNumber) &&
               Objects.equals(fromPersonName, that.fromPersonName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, amount, description, fromAccountNumber, fromPersonName);
    }
}
