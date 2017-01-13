package org.hacklist.util.socialApi.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author Neil Alishev
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class City {

    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
