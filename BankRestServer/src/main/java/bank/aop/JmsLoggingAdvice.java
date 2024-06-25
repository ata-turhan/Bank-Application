package bank.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import bank.logging.ILoggingService;

@Aspect
@Configuration
public class JmsLoggingAdvice {

    private final ILoggingService logger;

    @Autowired
    public JmsLoggingAdvice(ILoggingService logger) {
        this.logger = logger;
    }

    @After("execution(* bank.integration.jms.JMSSender.sendJMSMessage(..)) && args(message)")
    public void log(JoinPoint joinPoint, String message) {
        String methodName = joinPoint.getSignature().getName();
        String targetClass = joinPoint.getTarget().getClass().getSimpleName();
        logger.log("JMS Message sent by method: " + methodName + " of class: " + targetClass + ", Message: " + message);
    }
}
