package org.hacklist.service;

import org.hacklist.model.Token;
import org.hacklist.model.User;

/**
 * @author Aidar Shaifutdinov.
 */
public interface GitHubService {

    Token getToken(String code);

    User getUser(Token token);

}
