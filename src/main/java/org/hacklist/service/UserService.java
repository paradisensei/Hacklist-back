package org.hacklist.service;

import org.hacklist.model.User;
import org.hacklist.util.socialApi.SocialUser;

/**
 * @author Aidar Shaifutdinov.
 */
public interface UserService {

    User add(SocialUser socialUser, String clientToken);

    void update(User user, String clientToken);

}
