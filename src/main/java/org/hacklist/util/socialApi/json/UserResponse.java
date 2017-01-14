package org.hacklist.util.socialApi.json;

import org.hacklist.util.socialApi.user.VkUser;

import java.util.List;

/**
 * @author Neil Alishev
 */
public class UserResponse {

    private List<VkUser> response;

    public List<VkUser> getResponse() {
        return response;
    }

    public void setResponse(List<VkUser> response) {
        this.response = response;
    }

}
