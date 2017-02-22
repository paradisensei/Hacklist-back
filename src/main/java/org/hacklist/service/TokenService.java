package org.hacklist.service;

import org.hacklist.model.Token;
import org.hacklist.model.User;
import org.hacklist.model.enums.TokenType;
import org.hacklist.util.socialApi.user.SocialUser;

/**
 * @author Aidar Shaifutdinov.
 */
public interface TokenService {

    Token get(Long socialId, TokenType type);

    void add(Token token, TokenType type, SocialUser socialUser, User user);

    void update(Token oldToken, Token newToken);

}
