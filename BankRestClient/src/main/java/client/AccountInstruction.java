package client;

public class AccountInstruction {

    private long accountNumber;
    private String action;
    private String customerName;
    private double amount;

    // Default constructor for serialization/deserialization
    public AccountInstruction() {}

    // Constructor for customer-related actions
    public AccountInstruction(long accountNumber, String action, String customerName) {
        this.accountNumber = accountNumber;
        this.action = action;
        this.customerName = customerName;
    }

    // Constructor for amount-related actions
    public AccountInstruction(long accountNumber, String action, double amount) {
        this.accountNumber = accountNumber;
        this.action = action;
        this.amount = amount;
    }

    // Getters
    public long getAccountNumber() {
        return accountNumber;
    }

    public String getAction() {
        return action;
    }

    public String getCustomerName() {
        return customerName;
    }

    public double getAmount() {
        return amount;
    }

    // Setters
    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "AccountInstruction{" +
                "accountNumber=" + accountNumber +
                ", action='" + action + '\'' +
                ", customerName='" + customerName + '\'' +
                ", amount=" + amount +
                '}';
    }
}
