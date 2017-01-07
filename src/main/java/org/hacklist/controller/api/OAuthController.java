package org.hacklist.controller.api;

import org.hacklist.service.GitHubService;
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

    @Autowired
    public OAuthController(GitHubService gitHubService) {
        this.gitHubService = gitHubService;
    }

    @RequestMapping("/gitHub")
    public void gitHubCallback(@RequestParam("code") String code,
                               @RequestParam("state") String state) {
        gitHubService.acquireToken(code);
    }

}
