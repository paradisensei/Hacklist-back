package org.hacklist.util.vkApi;

import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * @author Neil Alishev
 */
@Component
@PropertySource("classpath:properties/oauth.properties")
public class VkOAuth implements EnvironmentAware {
    private Environment env;

    @Override
    public void setEnvironment(Environment environment) {
        this.env = environment;
    }

    public String clientId() {
        return env.getProperty("vk.clientId");
    }

    public String secret() {
        return env.getProperty("vk.secret");
    }

    public String tokenUrl() {
        return env.getProperty("vk.tokenUrl");
    }

    public String userUrl() {
        return env.getProperty("vk.userUrl");
    }

    public String redirectUri() {
        return env.getProperty("vk.redirectUri");
    }
}
