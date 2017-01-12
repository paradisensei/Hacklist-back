package org.hacklist.service.impl;

import org.hacklist.model.Token;
import org.hacklist.model.User;
import org.hacklist.model.enums.TokenType;
import org.hacklist.repository.TokenRepository;
import org.hacklist.service.TokenService;
import org.hacklist.util.gitHubApi.GitHubUser;
import org.hacklist.util.vkApi.VkUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @author Aidar Shaifutdinov.
 */
@Service
@Transactional
public class TokenServiceImpl implements TokenService {

    private final TokenRepository tokenRepository;

    @Autowired
    public TokenServiceImpl(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    @Override
    public void add(Token token, TokenType type,
                    GitHubUser gitHubUser, User user) {
        // 30 days in seconds
        token.setExpiresIn(86400 * 30);
        token.setSocialId(gitHubUser.getId());
        token.setDate(new Date());
        token.setActual(true);
        token.setType(type);
        token.setUser(user);
        tokenRepository.save(token);
    }

    @Override
    public void add(Token token, TokenType type, VkUser vkUser, User user) {
        // 24 hours in seconds
        token.setExpiresIn(86400);
        token.setSocialId(vkUser.getId());
        token.setDate(new Date());
        token.setActual(true);
        token.setType(type);
        token.setUser(user);
        tokenRepository.save(token);
    }

    @Override
    @Transactional(readOnly = true)
    public Token get(Long socialId, TokenType type) {
        return tokenRepository.findBySocialIdAndType(socialId, type);
    }

    @Override
    public void update(Token oldToken, Token newToken) {
        oldToken.setAccessToken(newToken.getAccessToken());
        oldToken.setDate(new Date());
        tokenRepository.save(oldToken);
    }

}
