package org.hacklist.service.impl;

import org.hacklist.model.Token;
import org.hacklist.model.User;
import org.hacklist.model.enums.TokenType;
import org.hacklist.repository.TokenRepository;
import org.hacklist.service.TokenService;
import org.hacklist.util.gitHubApi.GitHubUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author Aidar Shaifutdinov.
 */
@Service
public class TokenServiceImpl implements TokenService {

    private final TokenRepository tokenRepository;

    @Autowired
    public TokenServiceImpl(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    @Override
    public void add(Token token, TokenType type,
                    GitHubUser gitHubUser, User user) {
        if (type == TokenType.GITHUB) {
            // 30 days in seconds
            token.setExpiresIn(86400 * 30);
        }
        token.setSocialId(gitHubUser.getId());
        token.setDate(new Date());
        token.setActual(true);
        token.setType(type);
        token.setUser(user);
        tokenRepository.save(token);
    }

}
