package org.hacklist.util.forms;

import org.hacklist.model.enums.City;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Size;

/**
 * @author Neil Alishev
 */
public class HackForm {

    private static final String BLANK_MESSAGE = "This field is mandatory";

    private Long id;

    @NotBlank(message = BLANK_MESSAGE)
    @Size(min = 1, max = 50, message = "From 1 to 50 symbols")
    private String title;

    private City city;

    @NotBlank(message = BLANK_MESSAGE)
    private String address;

    @NotBlank(message = BLANK_MESSAGE)
    @Size(min = 1, max = 50, message = "From 1 to 50 symbols")
    private String organizer;

    @NotBlank(message = BLANK_MESSAGE)
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private String date;

    @NotBlank(message = BLANK_MESSAGE)
    @Size(min = 1, max = 300, message = "From 1 to 300 symbols")
    private String description;

    @NotBlank(message = BLANK_MESSAGE)
    @URL
    private String imageUrl;

    @NotBlank(message = BLANK_MESSAGE)
    @URL
    private String url;

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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
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
