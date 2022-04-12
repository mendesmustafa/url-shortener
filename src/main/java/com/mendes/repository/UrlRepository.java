package com.mendes.repository;

import com.mendes.model.entity.Url;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author mendes
 */

@Repository
public interface UrlRepository extends JpaRepository<Url, Long> {

    @Query("SELECT u FROM Url u WHERE lower(TRIM(u.shortUrl)) LIKE lower(CONCAT('%',:shortUrl,'%'))")
    List<Url> findByShortUrl(@Param("shortUrl") String shortUrl);
}
