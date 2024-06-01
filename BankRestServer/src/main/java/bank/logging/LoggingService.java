package bank.logging;

import org.springframework.stereotype.Service;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class LoggingService implements ILoggingService {

    private static final Logger bankLogger = Logger.getLogger(LoggingService.class.getName());

    @Override
    public void log(String logString) {
        bankLogger.log(Level.INFO, logString);
    }
}
