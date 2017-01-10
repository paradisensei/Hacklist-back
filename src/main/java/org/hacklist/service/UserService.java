package org.hacklist.service;

import org.hacklist.model.User;
import org.hacklist.util.gitHubApi.GitHubUser;

/**
 * @author Aidar Shaifutdinov.
 */
public interface UserService {

    User add(GitHubUser user, String clientToken);

}
