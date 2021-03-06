package org.hacklist.service.impl;

import org.apache.http.client.utils.URIBuilder;
import org.hacklist.model.Token;
import org.hacklist.service.VkService;
import org.hacklist.util.socialApi.user.SocialUser;
import org.hacklist.util.socialApi.props.VkOAuth;
import org.hacklist.util.socialApi.json.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

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
        URI tokenUrl = getURL(vkOAuth.tokenUrl() + code);
        return restTemplate.getForObject(tokenUrl, Token.class);
    }

    @Override
    public SocialUser getUser(Token token) {
        URI userUrl = getURL(vkOAuth.userUrl() + token.getAccessToken());
        return restTemplate.getForObject(userUrl, UserResponse.class)
                .getResponse().get(0);
    }

    private URI getURL(String url) {
        try {
            return new URIBuilder(url)
                    .addParameter("v", vkOAuth.version())
                    .build();
        } catch (URISyntaxException ignored) {
            return null;
        }
    }

}
