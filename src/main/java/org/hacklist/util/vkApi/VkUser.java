package org.hacklist.util.vkApi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Neil Alishev
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class VkUser {
    private Long id;

    @JsonProperty("first_name")
    private String name;

    private VkUserCity city;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public VkUserCity getCity() {
        return city;
    }

    public void setCity(VkUserCity city) {
        this.city = city;
    }
}
