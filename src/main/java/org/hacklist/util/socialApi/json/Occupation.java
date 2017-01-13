package org.hacklist.util.socialApi.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author Aidar Shaifutdinov.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Occupation {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
