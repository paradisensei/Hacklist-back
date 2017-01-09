package org.hacklist.service;

import org.hacklist.model.Token;
import org.hacklist.model.User;
import org.hacklist.model.enums.TokenType;

/**
 * @author Aidar Shaifutdinov.
 */
public interface TokenService {

    void add(Token token, User user, TokenType type);

}
