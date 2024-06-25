package bank.service;

import bank.domain.AccountChangeEvent;
import bank.dao.IAccountChangeEventDAO;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class EventPersistenceSaver {

    private final IAccountChangeEventDAO accountChangeEventDAO;

    public EventPersistenceSaver(IAccountChangeEventDAO accountChangeEventDAO) {
        this.accountChangeEventDAO = accountChangeEventDAO;
    }

    @EventListener
    public void handleAccountChangeEvent(AccountChangeEvent event) {
        System.out.println("Saving account change event: " + event);
        accountChangeEventDAO.save(event);
    }
}
