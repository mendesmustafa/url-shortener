package com.mendes.model.dto;

import java.io.Serializable;

/**
 * @author mendesmustafa on 24-02-2022
 */

public class UrlDto implements Serializable {

    private Long id;
    private String fullUrl;
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
