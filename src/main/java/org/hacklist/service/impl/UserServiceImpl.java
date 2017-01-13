package org.hacklist.service.impl;

import org.hacklist.model.User;
import org.hacklist.repository.UserRepository;
import org.hacklist.service.UserService;
import org.hacklist.util.socialApi.SocialUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Aidar Shaifutdinov.
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User add(SocialUser socialUser, String clientToken) {
        User user = new User(socialUser);
        user.setClientToken(clientToken);
        return userRepository.save(user);
    }

    @Override
    public void update(User user, String clientToken) {
        user.setClientToken(clientToken);
        userRepository.save(user);
    }

}
