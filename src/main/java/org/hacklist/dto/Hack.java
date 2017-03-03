package org.hacklist.dto;

import org.hacklist.model.enums.Category;
import org.hacklist.model.enums.City;

/**
 * @author Aidar Shaifutdinov.
 */
public class Hack {

    private Long id;

    private String title;

    private City city;

    private Category category;

    private String address;

    private String organizer;

    private Long date;

    private String description;

    private String imageUrl;

    private String url;

    public Hack() {
    }

    public Hack(org.hacklist.model.Hack hack) {
        id = hack.getId();
        title = hack.getTitle();
        city = hack.getCity();
        category = hack.getCategory();
        address = hack.getAddress();
        organizer = hack.getOrganizer();
        date = hack.getDate().getTime();
        description = hack.getDescription();
        imageUrl = hack.getImageUrl();
        url = hack.getUrl();
    }

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

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOrganizer() {
        return organizer;
    }

    public void setOrganizer(String organizer) {
        this.organizer = organizer;
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
