package bank.aop;

import bank.logging.ILoggingService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class DaoLoggingAdvice {

    private final ILoggingService logger;

    @Autowired
    public DaoLoggingAdvice(ILoggingService logger) {
        this.logger = logger;
    }

    @After("execution(* bank.dao.*.*(..))")
    public void log(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        String targetClass = joinPoint.getTarget().getClass().getSimpleName();
        logger.log("Call was made to method: " + methodName + " of class: " + targetClass);
    }
}
