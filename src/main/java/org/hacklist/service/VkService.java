package org.hacklist.service;

import org.hacklist.model.Token;
import org.hacklist.util.socialApi.user.SocialUser;

/**
 * @author Neil Alishev
 */
public interface VkService {

    Token getToken(String code);

    SocialUser getUser(Token token);

}
