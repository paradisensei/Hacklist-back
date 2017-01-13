package org.hacklist.util.socialApi.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hacklist.util.socialApi.json.City;
import org.hacklist.util.socialApi.json.Occupation;

/**
 * @author Neil Alishev
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class VkUser implements SocialUser {

    private Long id;

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("last_name")
    private String lastName;

    private Occupation occupation;

    private City city;

    private String about;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return firstName + " " + lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String getEmail() {
        return null;
    }

    @Override
    public String getCompany() {
        return occupation != null ? occupation.getName() : null;
    }

    public void setOccupation(Occupation occupation) {
        this.occupation = occupation;
    }

    @Override
    public String getLocation() {
        return city != null ? city.getTitle() : null;
    }

    public void setCity(City city) {
        this.city = city;
    }

    @Override
    public String getBio() {
        return about.isEmpty() ? null : about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

}
