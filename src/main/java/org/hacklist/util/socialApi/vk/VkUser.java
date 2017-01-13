package org.hacklist.util.socialApi.vk;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hacklist.util.socialApi.SocialUser;

/**
 * @author Neil Alishev
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class VkUser implements SocialUser {

    private Long id;

    @JsonProperty("first_name")
    private String name;

    private VkUserCity city;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getEmail() {
        return null;
    }

    @Override
    public String getCompany() {
        return null;
    }

    @Override
    public String getLocation() {
        return null;
    }

    @Override
    public String getBio() {
        return null;
    }

    public VkUserCity getCity() {
        return city;
    }

    public void setCity(VkUserCity city) {
        this.city = city;
    }

}
