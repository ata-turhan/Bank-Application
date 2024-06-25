package jms;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@PropertySource("classpath:application.properties")
@EnableJms
public class SpringJmsReceiverApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(SpringJmsReceiverApplication.class, args);
    }

    @Override
    public void run(String... args) {
        System.out.println("Receiver has started ...");
    }
}
