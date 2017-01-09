package org.hacklist.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

/**
 * @author Aidar Shaifutdinov.
 */
@Entity
@Table(name = "users")
@SequenceGenerator(name = "users_gen", sequenceName = "users_seq")
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_gen")
    private Long id;

    private String name;

    private String email;

    private String company;

    private String location;

    private String bio;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", company='" + company + '\'' +
                ", location='" + location + '\'' +
                ", bio='" + bio + '\'' +
                '}';
    }

}
