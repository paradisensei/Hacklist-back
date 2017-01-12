package org.hacklist.util.vkApi;

import java.util.List;

/**
 * @author Neil Alishev
 */
public class VkUserResponse {
    private List<VkUser> response;

    public List<VkUser> getResponse() {
        return response;
    }

    public void setResponse(List<VkUser> response) {
        this.response = response;
    }
}
