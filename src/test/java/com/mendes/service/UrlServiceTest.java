package com.mendes.service;

import com.mendes.model.Url;
import com.mendes.repository.UrlRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author mendes
 */

@SpringBootTest
class UrlServiceTest {

    private static final String FULL_URL = "DENEME";
    private static final String SHORT_URL = "DENE";

    Url defaultUrl;
    Url resultUrl;

    @Autowired
    private UrlService urlService;

    @Autowired
    private UrlRepository urlRepository;


    @BeforeEach
    public void setUp() {
        defaultUrl = new Url();
        defaultUrl.setFullUrl(FULL_URL);
        defaultUrl.setShortUrl(SHORT_URL);
    }

    @AfterEach
    public void clear() {
        if (resultUrl != null && resultUrl.getId() != null) {
            urlService.delete(resultUrl.getId());
        }
    }

    @Test
    public void save() {
        resultUrl = urlService.save(defaultUrl);
        assertNotNull(resultUrl);
    }

    @Test
    public void delete() {
        defaultUrl = urlService.save(defaultUrl);
        urlService.delete(defaultUrl.getId());
        Optional<Url> url = urlRepository.findById(defaultUrl.getId());
        assertFalse(url.isPresent());
    }

    @Test
    public void list() {
        resultUrl = urlService.save(defaultUrl);
        assertNotNull(resultUrl);

        List<Url> urls = urlService.list();
        assertTrue(urls.size() > 0);
    }

    @Test
    public void search() {
        resultUrl = urlService.save(defaultUrl);
        assertNotNull(resultUrl);

        Url url = urlService.findByShortUrl(SHORT_URL);
        assertNotNull(url);
    }
}