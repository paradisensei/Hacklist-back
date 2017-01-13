package org.hacklist.controller.api;

import org.hacklist.model.Token;
import org.hacklist.model.User;
import org.hacklist.model.enums.TokenType;
import org.hacklist.service.GitHubService;
import org.hacklist.service.TokenService;
import org.hacklist.service.UserService;
import org.hacklist.service.VkService;
import org.hacklist.util.socialApi.SocialUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Aidar Shaifutdinov.
 */
@RestController
@RequestMapping("/api/oauth")
public class OAuthController {

    private final GitHubService gitHubService;
    private final VkService vkService;
    private final UserService userService;
    private final TokenService tokenService;

    @Autowired
    public OAuthController(GitHubService gitHubService, VkService vkService,
                           UserService userService, TokenService tokenService) {
        this.gitHubService = gitHubService;
        this.vkService = vkService;
        this.userService = userService;
        this.tokenService = tokenService;
    }

    @RequestMapping("/gitHub")
    public void gitHubCallback(@RequestParam("code") String code,
                               @RequestParam("state") String clientToken) {
        Token token = gitHubService.getToken(code);
        SocialUser socialUser = gitHubService.getUser(token);
        addUserAndToken(token, socialUser, TokenType.GITHUB, clientToken);
    }

    @RequestMapping("/vk")
    public void vkCallback(@RequestParam("code") String code,
                           @RequestParam("state") String clientToken) {
        Token token = vkService.getToken(code);
        SocialUser socialUser = vkService.getUser(token);
        addUserAndToken(token, socialUser, TokenType.VK, clientToken);
    }

    private void addUserAndToken(Token newToken, SocialUser socialUser,
                                 TokenType tokenType, String clientToken) {
        Token oldToken = tokenService.get(socialUser.getId(), tokenType);
        if (oldToken == null) {
            User user = userService.add(socialUser, clientToken);
            tokenService.add(newToken, tokenType, socialUser, user);
        } else {
            userService.update(oldToken.getUser(), clientToken);
            tokenService.update(oldToken, newToken);
        }
    }

}
