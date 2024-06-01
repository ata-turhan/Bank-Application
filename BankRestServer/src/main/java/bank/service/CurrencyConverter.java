package bank.service;

import org.springframework.stereotype.Service;

@Service
public class CurrencyConverter implements ICurrencyConverter {

    private static final double EURO_TO_DOLLAR_RATE = 1.1;
    private static final double DOLLAR_TO_EURO_RATE = 0.9;

    @Override
    public double euroToDollars(double amount) {
        double convertedAmount = EURO_TO_DOLLAR_RATE * amount;
        System.out.println("CurrencyConverter: converting " + amount + " euros to dollars. Converted amount: " + convertedAmount);
        return convertedAmount;
    }

    @Override
    public double dollarsToEuros(double amount) {
        double convertedAmount = DOLLAR_TO_EURO_RATE * amount;
        System.out.println("CurrencyConverter: converting " + amount + " dollars to euros. Converted amount: " + convertedAmount);
        return convertedAmount;
    }
}
