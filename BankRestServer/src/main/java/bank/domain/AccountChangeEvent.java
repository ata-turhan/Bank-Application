package bank.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
public class AccountChangeEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private double amount;
    private long accountNumber;
    private String operation;
    private Date dateTime;

    @PrePersist
    protected void onCreate() {
        dateTime = new Date();
    }

    public AccountChangeEvent() {
        // Default constructor for JPA
    }

    public AccountChangeEvent(String operation, long accountNumber, double amount) {
        this.operation = operation;
        this.accountNumber = accountNumber;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "AccountChangeEvent{" +
                "dateTime=" + dateTime +
                ", accountNumber=" + accountNumber +
                ", operation='" + operation + '\'' +
                ", amount=" + amount +
                '}';
    }

    // Getters and Setters

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }
}
