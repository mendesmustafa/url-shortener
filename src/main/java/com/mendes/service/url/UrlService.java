package com.mendes.service.url;

import com.mendes.model.dto.UrlDto;

import java.util.List;

/**
 * @author mendesmustafa on 22-03-2022
 */

public interface UrlService {

    UrlDto save(UrlDto model);

    List<UrlDto> list();

    void delete(Long id);

    UrlDto findByShortUrl(String shortUrl);

}
