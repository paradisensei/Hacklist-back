package org.hacklist.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hacklist.model.enums.TokenType;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Aidar Shaifutdinov.
 */
@Entity
@Table(name = "token")
@SequenceGenerator(name = "token_gen", sequenceName = "token_seq")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "token_gen")
    private Long id;

    @Column(name = "access_token")
    @JsonProperty("access_token")
    private String accessToken;

    @Column(name = "expires_in")
    @JsonProperty("expires_in")
    private int expiresIn;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "birth_date")
    private Date date;

    private boolean actual;

    @Enumerated(EnumType.STRING)
    private TokenType type;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public int getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(int expiresIn) {
        this.expiresIn = expiresIn;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isActual() {
        return actual;
    }

    public void setActual(boolean actual) {
        this.actual = actual;
    }

    public TokenType getType() {
        return type;
    }

    public void setType(TokenType type) {
        this.type = type;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Token{" +
                "id=" + id +
                ", accessToken='" + accessToken + '\'' +
                ", expiresIn=" + expiresIn +
                ", date=" + date +
                ", actual=" + actual +
                ", type=" + type +
                ", user=" + user +
                '}';
    }

}
