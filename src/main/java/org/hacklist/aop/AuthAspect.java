package org.hacklist.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.hacklist.util.misc.CountdownLatchSingleton;
import org.springframework.stereotype.Component;

/**
 * @author Neil Alishev
 */
@Component
@Aspect
public class AuthAspect {

    @Around("execution(void org.hacklist.controller.api.OAuthController.*(..))")
    public void test(ProceedingJoinPoint pjp) {
        CountdownLatchSingleton.init();
        try {
            pjp.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        CountdownLatchSingleton.getCountDownLatch().countDown();
    }
}
