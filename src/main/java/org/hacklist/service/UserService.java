package org.hacklist.service;

import org.hacklist.model.User;
import org.hacklist.util.socialApi.user.SocialUser;

/**
 * @author Aidar Shaifutdinov.
 */
public interface UserService {

    User add(SocialUser socialUser, String clientToken);

    User getOneByClientToken(String clientToken);

    void update(User user, String clientToken);

}
