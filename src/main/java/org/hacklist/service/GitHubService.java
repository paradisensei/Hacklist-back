package org.hacklist.service;

import org.hacklist.model.Token;
import org.hacklist.util.socialApi.user.SocialUser;

/**
 * @author Aidar Shaifutdinov.
 */
public interface GitHubService {

    Token getToken(String code);

    SocialUser getUser(Token token);

}
