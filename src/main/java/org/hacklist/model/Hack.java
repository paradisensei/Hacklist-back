package org.hacklist.model;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Aidar Shaifutdinov.
 */
@Entity
@Table(name = "hack")
public class Hack {

    @Id
    private Long id;

    private String title;

    private String city;

    private String address;

    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
