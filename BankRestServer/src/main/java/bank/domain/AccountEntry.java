package bank.domain;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
public class AccountEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    private double amount;
    private String description;
    private String fromAccountNumber;
    private String fromPersonName;

    public AccountEntry() {
        // Default constructor for JPA
    }

    public AccountEntry(Date date, double amount, String description, String fromAccountNumber, String fromPersonName) {
        this.date = date;
        this.amount = amount;
        this.description = description;
        this.fromAccountNumber = fromAccountNumber;
        this.fromPersonName = fromPersonName;
    }

    public long getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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
        return String.format("AccountEntry{id=%d, date=%s, amount=%.2f, description='%s', fromAccountNumber='%s', fromPersonName='%s'}",
                id, date, amount, description, fromAccountNumber, fromPersonName);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AccountEntry)) return false;
        AccountEntry that = (AccountEntry) o;
        return id == that.id &&
                Double.compare(that.amount, amount) == 0 &&
                Objects.equals(date, that.date) &&
                Objects.equals(description, that.description) &&
                Objects.equals(fromAccountNumber, that.fromAccountNumber) &&
                Objects.equals(fromPersonName, that.fromPersonName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, amount, description, fromAccountNumber, fromPersonName);
    }
}
