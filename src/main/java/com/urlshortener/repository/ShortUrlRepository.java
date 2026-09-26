package com.urlshortener.repository;

import com.urlshortener.domain.ShortUrl;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/** Spring Data JPA repository interface for ShortUrl database operations. */
@Repository
public interface ShortUrlRepository extends JpaRepository<ShortUrl, Long> {

  /**
   * Finds an active short link by its unique code.
   *
   * @param code The short code identifier.
   * @return An Optional containing the active ShortUrl if found.
   */
  Optional<ShortUrl> findByCodeAndActiveTrue(String code);

  /**
   * Finds a short link by its unique code regardless of active status.
   *
   * @param code The short code identifier.
   * @return An Optional containing the ShortUrl if found.
   */
  Optional<ShortUrl> findByCode(String code);
}
