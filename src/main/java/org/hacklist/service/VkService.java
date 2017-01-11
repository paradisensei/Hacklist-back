package org.hacklist.service;

import org.hacklist.model.Token;
import org.hacklist.util.vkApi.VkUser;

/**
 * @author Neil Alishev
 */
public interface VkService {

    Token getToken(String code);

    VkUser getUser(Token token);

}
