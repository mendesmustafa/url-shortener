package com.mendes.service.url;

import com.mendes.model.dto.UrlDto;
import com.mendes.model.entity.Url;
import com.mendes.repository.UrlRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author mendes
 */

@Service
public class UrlServiceImpl implements UrlService {

    private final UrlRepository urlRepository;

    public UrlServiceImpl(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    public UrlDto save(UrlDto model) {
        Url url = new Url();
        url.setShortUrl(model.getShortUrl());
        url.setFullUrl(model.getFullUrl());
        urlRepository.save(url);
        model.setId(url.getId());
        return model;
    }

    public List<UrlDto> list() {
        List<UrlDto> urlDtos = new ArrayList<>();
        urlRepository.findAll().forEach(url -> {
            UrlDto urlDto = convertToUrlDto(url);
            urlDtos.add(urlDto);
        });
        return urlDtos;
    }

    public void delete(Long id) {
        Optional<Url> optionalUrl = urlRepository.findById(id);
        if (optionalUrl.isPresent()) {
            urlRepository.deleteById(id);
        }
    }

    public UrlDto findByShortUrl(String shortUrl) {
        List<UrlDto> urlDtos = new ArrayList<>();
        List<Url> urls = urlRepository.findByShortUrl(shortUrl);
        urls.forEach(url -> {
            UrlDto urlDto = convertToUrlDto(url);
            urlDtos.add(urlDto);
        });
        if (!urlDtos.isEmpty()) {
            return urlDtos.get(0);
        }
        return null;
    }

    private UrlDto convertToUrlDto(Url url) {
        UrlDto urlDto = new UrlDto();
        urlDto.setId(url.getId());
        urlDto.setShortUrl(url.getShortUrl());
        urlDto.setFullUrl(url.getFullUrl());
        return urlDto;
    }
}
