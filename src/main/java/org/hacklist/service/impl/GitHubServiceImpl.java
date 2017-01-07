package org.hacklist.service.impl;

import org.hacklist.service.GitHubService;
import org.hacklist.util.oauth.GitHubOAuth;
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
    public void acquireToken(String code) {
        MultiValueMap<String, Object> params = new LinkedMultiValueMap<>();
        params.add("client_id", gitHubOAuth.clientId());
        params.add("client_secret", gitHubOAuth.secret());
        params.add("code", code);
        Object res = restTemplate.postForObject(gitHubOAuth.tokenUrl(), params, Object.class);
        //TODO save user information
        System.out.println("Token : " + res);
    }

}
