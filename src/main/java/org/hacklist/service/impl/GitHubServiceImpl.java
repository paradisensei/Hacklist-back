package org.hacklist.service.impl;

import org.hacklist.model.Token;
import org.hacklist.service.GitHubService;
import org.hacklist.util.socialApi.user.GitHubUser;
import org.hacklist.util.socialApi.user.SocialUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * @author Aidar Shaifutdinov.
 */
@Service
public class GitHubServiceImpl implements GitHubService {

    private final Environment env;
    private final RestTemplate restTemplate;

    @Autowired
    public GitHubServiceImpl(Environment env, RestTemplate restTemplate) {
        this.env = env;
        this.restTemplate = restTemplate;
    }

    @Override
    public Token getToken(String code) {
        MultiValueMap<String, Object> params = new LinkedMultiValueMap<>();
        params.add("client_id", env.getProperty("gitHub.clientId"));
        params.add("client_secret", env.getProperty("gitHub.secret"));
        params.add("code", code);
        String tokenUrl = env.getProperty("gitHub.tokenUrl");
        return restTemplate.postForObject(tokenUrl, params, Token.class);
    }

    @Override
    public SocialUser getUser(Token token) {
        String userUrl = env.getProperty("gitHub.userUrl") + token.getAccessToken();
        return restTemplate.getForObject(userUrl, GitHubUser.class);
    }

}
