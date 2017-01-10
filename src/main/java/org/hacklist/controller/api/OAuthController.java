package org.hacklist.controller.api;

import org.hacklist.model.Token;
import org.hacklist.model.User;
import org.hacklist.model.enums.TokenType;
import org.hacklist.service.GitHubService;
import org.hacklist.service.TokenService;
import org.hacklist.service.UserService;
import org.hacklist.util.gitHubApi.GitHubUser;
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
    private final UserService userService;
    private final TokenService tokenService;

    @Autowired
    public OAuthController(GitHubService gitHubService, UserService userService,
                           TokenService tokenService) {
        this.gitHubService = gitHubService;
        this.userService = userService;
        this.tokenService = tokenService;
    }

    @RequestMapping("/gitHub")
    public void gitHubCallback(@RequestParam("code") String code,
                               @RequestParam("state") String clientToken) {
        Token token = gitHubService.getToken(code);
        GitHubUser gitHubUser = gitHubService.getUser(token);
        System.out.println(token);
        System.out.println(gitHubUser);
        //TODO check if user already exists in the db
        User user = userService.add(gitHubUser, clientToken);
        tokenService.add(token, TokenType.GITHUB, gitHubUser, user);
    }

}
