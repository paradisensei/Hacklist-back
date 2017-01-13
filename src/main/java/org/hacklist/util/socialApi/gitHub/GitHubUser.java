package org.hacklist.util.socialApi.gitHub;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hacklist.util.socialApi.SocialUser;

/**
 * @author Aidar Shaifutdinov.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GitHubUser implements SocialUser {

    private Long id;

    private String name;

    private String email;

    private String company;

    private String location;

    private String bio;

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
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    @Override
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    @Override
    public String toString() {
        return "GitHubUser{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", company='" + company + '\'' +
                ", location='" + location + '\'' +
                ", bio='" + bio + '\'' +
                '}';
    }

}
