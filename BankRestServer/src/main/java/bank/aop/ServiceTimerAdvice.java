package bank.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StopWatch;

@Aspect
@Configuration
public class ServiceTimerAdvice {

    @Around("execution(* bank.service.*.*(..))")
    public Object time(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start(proceedingJoinPoint.getSignature().getName());
        Object returnValue = proceedingJoinPoint.proceed();
        stopWatch.stop();

        long totalTimeMillis = stopWatch.getTotalTimeMillis();
        System.out.println("Time to execute " + proceedingJoinPoint.getSignature().getName() + " = " + totalTimeMillis + " ms");

        return returnValue;
    }
}
