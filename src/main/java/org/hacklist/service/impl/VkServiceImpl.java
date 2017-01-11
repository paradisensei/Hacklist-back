package org.hacklist.service.impl;

import org.hacklist.model.Token;
import org.hacklist.service.VkService;
import org.hacklist.util.vkApi.VkOAuth;
import org.hacklist.util.vkApi.VkUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * @author Neil Alishev
 */
@Service
public class VkServiceImpl implements VkService {

    private final RestTemplate restTemplate;
    private final VkOAuth vkOAuth;

    @Autowired
    public VkServiceImpl(RestTemplate restTemplate, VkOAuth vkOAuth) {
        this.restTemplate = restTemplate;
        this.vkOAuth = vkOAuth;
    }

    @Override
    public Token getToken(String code) {
        MultiValueMap<String, Object> params = new LinkedMultiValueMap<>();
        params.add("client_id", vkOAuth.clientId());
        params.add("client_secret", vkOAuth.secret());
        params.add("redirect_uri", vkOAuth.redirectUri());
        params.add("code", code);
        String tokenUrl = vkOAuth.tokenUrl();
        return restTemplate.postForObject(tokenUrl, params, Token.class);
    }

    @Override
    public VkUser getUser(Token token) {
        String userUrl = vkOAuth.userUrl() + token.getAccessToken();
//        Не получаю юзера. Access denied: no access to call this method
        return restTemplate.getForObject(userUrl, VkUser.class);
    }
}
