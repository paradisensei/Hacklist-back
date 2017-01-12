package org.hacklist.service;

import org.hacklist.model.User;
import org.hacklist.util.gitHubApi.GitHubUser;
import org.hacklist.util.vkApi.VkUser;

/**
 * @author Aidar Shaifutdinov.
 */
public interface UserService {

    User add(GitHubUser user, String clientToken);

    User add(VkUser vkUser, String clientToken);

    void update(User user, String clientToken);

}
