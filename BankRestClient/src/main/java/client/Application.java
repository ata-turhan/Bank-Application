package client;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootApplication
@EnableKafka
public class Application implements CommandLineRunner {
    private final BankGateway bankGateway;
    private final Sender sender;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    public Application(BankGateway bankGateway, Sender sender) {
        this.bankGateway = bankGateway;
        this.sender = sender;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception  {
        createAndUseAccounts();
        showAccountBalances();
    }

    private void createAndUseAccounts() throws Exception {
        AccountInstruction accountInstruction;

        bankGateway.createAccount(1, "Alice Smith");
        accountInstruction = new AccountInstruction(1, "createAccount","Alice Smith");
        sendKafkaMessage(accountInstruction);

        bankGateway.createAccount(2, "Bob Johnson");
        accountInstruction = new AccountInstruction(2, "createAccount","Bob Johnson");
        sendKafkaMessage(accountInstruction);

        bankGateway.deposit(1, 300);
        accountInstruction = new AccountInstruction(1, "deposit",300);
        sendKafkaMessage(accountInstruction);
        
        bankGateway.deposit(1, 600);
        accountInstruction = new AccountInstruction(1, "deposit",600);
        sendKafkaMessage(accountInstruction);

        try {
            bankGateway.withdrawEuros(1, 250);
            accountInstruction = new AccountInstruction(1, "withdraw",250);
            sendKafkaMessage(accountInstruction);
        } catch (InsufficientFundsException e) {
            System.out.println(e.getMessage());
        }

        bankGateway.deposit(2, 15000);
        accountInstruction = new AccountInstruction(2, "deposit",15000);
        sendKafkaMessage(accountInstruction);

        bankGateway.depositEuros(2, 300);
        accountInstruction = new AccountInstruction(2, "deposit",300);
        sendKafkaMessage(accountInstruction);

        try {
            bankGateway.transferFunds(2, 1, 200, "Payment for services");
        } catch (InsufficientFundsException e) {
            System.out.println(e.getMessage());
        }
    }

    private void showAccountBalances() {
        Collection<AccountDTO> accountList = bankGateway.getAllAccounts();
        for (AccountDTO account : accountList) {
            CustomerDTO customer = account.getCustomer();
            System.out.println("Statement for Account: " + account.getAccountNumber());
            System.out.println("Account Holder: " + customer.getName());
            System.out.println("-Date-------------------------" + "-Description------------------" + "-Amount-------------");
            for (AccountEntryDTO entry : account.getEntryList()) {
                System.out.printf("%30s%30s%20.2f\n", entry.getDate().toString(), entry.getDescription(), entry.getAmount());
            }
            System.out.println("----------------------------------------" + "----------------------------------------");
            System.out.printf("%30s%30s%20.2f\n\n", "", "Current Balance:", account.getBalance());
        }
    }

    private void sendKafkaMessage(AccountInstruction accountInstruction) throws Exception {
        String accountInstructionAsString = objectMapper.writeValueAsString(accountInstruction);
        System.out.println("Sending a Kafka message: " + accountInstructionAsString);
        sender.send("accountTopic", accountInstructionAsString);
    }

}
