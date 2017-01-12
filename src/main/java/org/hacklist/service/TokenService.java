package org.hacklist.service;

import org.hacklist.model.Token;
import org.hacklist.model.User;
import org.hacklist.model.enums.TokenType;
import org.hacklist.util.gitHubApi.GitHubUser;
import org.hacklist.util.vkApi.VkUser;

/**
 * @author Aidar Shaifutdinov.
 */
public interface TokenService {

    void add(Token token, TokenType type,
             GitHubUser gitHubUser, User user);

    void add(Token token, TokenType type,
             VkUser vkUser, User user);

    Token get(Long socialId, TokenType type);

    void update(Token oldToken, Token newToken);

}
