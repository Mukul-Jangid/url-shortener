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

  /**
   * Checks if a short link exists with the given code.
   *
   * @param code The short code identifier.
   * @return true if a record exists with the code, false otherwise.
   */
  boolean existsByCode(String code);

  /**
   * Atomically increments the click count and updates the last accessed timestamp for a short link
   * code.
   *
   * @param code The short code identifier.
   * @param now Current timestamp when the link was accessed.
   * @return The number of rows updated (1 if found and active, 0 otherwise).
   */
  @org.springframework.data.jpa.repository.Modifying
  @org.springframework.data.jpa.repository.Query(
      "UPDATE ShortUrl s SET s.clickCount = s.clickCount + 1, s.lastAccessedAt = :now WHERE s.code = :code AND s.active = true")
  int incrementClickCount(
      @org.springframework.data.repository.query.Param("code") String code,
      @org.springframework.data.repository.query.Param("now") java.time.Instant now);
}
