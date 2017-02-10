package org.hacklist.log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author Aidar Shaifutdinov.
 */
@Component
@Aspect
public class LogAspect {

    private final static Logger logger = LoggerFactory.getLogger(LogAspect.class);

    // GitHub and Vk callbacks
    @After("execution(public void org.hacklist.controller.api.OAuthController.*(..))")
    public void logOAuth(JoinPoint jp) {
        String token = jp.getArgs()[1].toString();
        logger.info("User with token=\"" + token + "\" persisted");
    }

    @Before("execution(* org.hacklist.controller.api.HackController.getHackList(..))")
    public void logGetHacks(JoinPoint jp) {
        String token = jp.getArgs()[0].toString();
        logger.info("User with token=\"" + token + "\" tries to get hacks");
    }

    @Before("execution(* org.hacklist.controller.api.HackController.handleException(..))")
    public void logAuthFailure() {
        logger.warn("Someone failed to get hacks due to failed auth");
    }

}
