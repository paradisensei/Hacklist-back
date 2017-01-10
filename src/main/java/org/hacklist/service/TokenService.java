package org.hacklist.service;

import org.hacklist.model.Token;
import org.hacklist.model.User;
import org.hacklist.model.enums.TokenType;
import org.hacklist.util.gitHubApi.GitHubUser;

/**
 * @author Aidar Shaifutdinov.
 */
public interface TokenService {

    void add(Token token, TokenType type,
             GitHubUser gitHubUser, User user);

}
