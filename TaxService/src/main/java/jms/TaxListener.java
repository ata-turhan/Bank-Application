package jms;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class TaxListener {

    @JmsListener(destination = "taxQueue")
    public void handleTaxMessage(String message) {
        System.out.println("Tax Service receives message: " + message);
    }
}
