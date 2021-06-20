package com.mendes.service;

import com.mendes.model.Url;
import com.mendes.repository.UrlRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author mendes
 */

@Service
public class UrlService {

    private UrlRepository urlRepository;

    public UrlService(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    public Url save(Url model) {
        Url url = urlRepository.save(model);
        return url;
    }

    public List<Url> list() {
        List<Url> urls = urlRepository.findAll();
        return urls;
    }

    public void delete(Long id) {
        urlRepository.deleteById(id);
    }

    public Url findByShortUrl(String shortUrl) {
        Url url = null;
        List<Url> urls = urlRepository.findByShortUrl(shortUrl);
        if (urls != null && !urls.isEmpty())
            url = urls.get(0);
        return url;
    }
}
