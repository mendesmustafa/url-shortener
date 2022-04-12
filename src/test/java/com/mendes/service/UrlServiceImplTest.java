package com.mendes.service;

import com.mendes.model.dto.UrlDto;
import com.mendes.model.entity.Url;
import com.mendes.repository.UrlRepository;
import com.mendes.service.url.UrlServiceImpl;
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
class UrlServiceImplTest {

    private static final String FULL_URL = "TEST-FULL-URL";
    private static final String SHORT_URL = "TEST-SHORT-URL";

    UrlDto defaultUrlDto;
    UrlDto resultUrlDto;

    @Autowired
    private UrlServiceImpl urlServiceImpl;

    @Autowired
    private UrlRepository urlRepository;

    @BeforeEach
    public void setUp() {
        defaultUrlDto = new UrlDto();
        defaultUrlDto.setFullUrl(FULL_URL);
        defaultUrlDto.setShortUrl(SHORT_URL);
    }

    @AfterEach
    public void clear() {
        if (resultUrlDto != null && resultUrlDto.getId() != null) {
            urlServiceImpl.delete(resultUrlDto.getId());
        }
    }

    @Test
    void save() {
        resultUrlDto = urlServiceImpl.save(defaultUrlDto);
        assertNotNull(resultUrlDto);
    }

    @Test
    void delete() {
        resultUrlDto = urlServiceImpl.save(defaultUrlDto);
        assertNotNull(resultUrlDto.getId());
        urlServiceImpl.delete(resultUrlDto.getId());
        Optional<Url> optionalUrl = urlRepository.findById(resultUrlDto.getId());
        assertTrue(optionalUrl.isEmpty());
    }

    @Test
    void list() {
        resultUrlDto = urlServiceImpl.save(defaultUrlDto);
        assertNotNull(resultUrlDto.getId());
        List<UrlDto> urls = urlServiceImpl.list();
        assertTrue(urls.size() > 0);
        assertEquals(1, urls.size());
    }

    @Test
    void search() {
        resultUrlDto = urlServiceImpl.save(defaultUrlDto);
        assertNotNull(resultUrlDto.getId());
        UrlDto url = urlServiceImpl.findByShortUrl(SHORT_URL);
        assertAll(
                () -> assertNotNull(url),
                () -> assertEquals(SHORT_URL, url.getShortUrl())
        );
    }
}