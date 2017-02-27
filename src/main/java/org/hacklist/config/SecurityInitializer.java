package org.hacklist.config;

import org.springframework.core.annotation.Order;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

/**
 * @author Neil Alishev
 */
@Order(2)
public class SecurityInitializer extends AbstractSecurityWebApplicationInitializer {
}
