package bank.service;

import bank.domain.AccountChangeEvent;
import bank.integration.email.EmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class EventEmailer {
    
    private final EmailSender emailSender;

    @Autowired
    public EventEmailer(EmailSender emailSender) {
        this.emailSender = emailSender;
    }

    @EventListener
    public void handleAccountChangeEvent(AccountChangeEvent event) {
        emailSender.sendEmail("Notification: Account change event: " + event.toString());
    }
}
