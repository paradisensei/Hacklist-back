package org.hacklist.util.socialApi.props;

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

    public String version() {
        return env.getProperty("vk.version");
    }

    public String tokenUrl() {
        return env.getProperty("vk.tokenUrl");
    }

    public String userUrl() {
        return env.getProperty("vk.userUrl");
    }

}
