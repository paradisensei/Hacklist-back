package org.hacklist.util.socialApi.props;

import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * @author Aidar Shaifutdinov.
 */
@Component
@PropertySource("classpath:properties/firebase.properties")
public class Firebase implements EnvironmentAware {

    private Environment env;

    @Override
    public void setEnvironment(Environment env) {
        this.env = env;
    }

    public String getUrl() {
        return env.getProperty("firebase.url");
    }

    public String getKey() {
        return env.getProperty("firebase.key");
    }

}
