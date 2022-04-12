package com.mendes.model.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author mendes
 */

@Entity
@Table(name = "URL")
public class Url implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "URL_ID_SEQ")
    @SequenceGenerator(name = "URL_ID_SEQ", allocationSize = 1)
    private Long id;

    @Column(name = "FULL_URL")
    private String fullUrl;

    @Column(name = "SHORT_URL")
    private String shortUrl;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullUrl() {
        return fullUrl;
    }

    public void setFullUrl(String fullUrl) {
        this.fullUrl = fullUrl;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }
}
