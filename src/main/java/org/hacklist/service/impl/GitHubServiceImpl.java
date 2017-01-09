package org.hacklist.service.impl;

import org.hacklist.model.Token;
import org.hacklist.model.User;
import org.hacklist.service.GitHubService;
import org.hacklist.util.gitHubApi.GitHubOAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * @author Aidar Shaifutdinov.
 */
@Service
public class GitHubServiceImpl implements GitHubService {

    private final RestTemplate restTemplate;
    private final GitHubOAuth gitHubOAuth;

    @Autowired
    public GitHubServiceImpl(RestTemplate restTemplate, GitHubOAuth gitHubOAuth) {
        this.restTemplate = restTemplate;
        this.gitHubOAuth = gitHubOAuth;
    }

    @Override
    public Token getToken(String code) {
        MultiValueMap<String, Object> params = new LinkedMultiValueMap<>();
        params.add("client_id", gitHubOAuth.clientId());
        params.add("client_secret", gitHubOAuth.secret());
        params.add("code", code);
        String tokenUrl = gitHubOAuth.tokenUrl();
        return restTemplate.postForObject(tokenUrl, params, Token.class);
    }

    @Override
    public User getUser(Token token) {
        String userUrl = gitHubOAuth.userUrl() + token.getAccessToken();
        return restTemplate.getForObject(userUrl, User.class);
    }

}
