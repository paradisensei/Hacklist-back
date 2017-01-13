package org.hacklist.service.impl;

import org.hacklist.model.Token;
import org.hacklist.service.VkService;
import org.hacklist.util.socialApi.SocialUser;
import org.hacklist.util.socialApi.vk.VkOAuth;
import org.hacklist.util.socialApi.vk.VkUserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author Neil Alishev
 */
@Service
public class VkServiceImpl implements VkService {

    private final VkOAuth vkOAuth;
    private final RestTemplate restTemplate;

    @Autowired
    public VkServiceImpl(RestTemplate restTemplate, VkOAuth vkOAuth) {
        this.restTemplate = restTemplate;
        this.vkOAuth = vkOAuth;
    }

    @Override
    public Token getToken(String code) {
        String tokenUrl = vkOAuth.tokenUrl() + code;
        return restTemplate.getForObject(tokenUrl, Token.class);
    }

    @Override
    public SocialUser getUser(Token token) {
        String userUrl = vkOAuth.userUrl() + token.getAccessToken();
        return restTemplate.getForObject(userUrl, VkUserResponse.class).getResponse().get(0);
    }
}
