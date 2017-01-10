package org.hacklist.service;

import org.hacklist.model.Token;
import org.hacklist.util.gitHubApi.GitHubUser;

/**
 * @author Aidar Shaifutdinov.
 */
public interface GitHubService {

    Token getToken(String code);

    GitHubUser getUser(Token token);

}
