package com.urlshortener.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.urlshortener.domain.ShortUrl;
import java.time.Instant;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

@DataJpaTest
class ShortUrlRepositoryTest {

  @Autowired private ShortUrlRepository shortUrlRepository;

  @Test
  @DisplayName("Should save and retrieve a ShortUrl by ID")
  void save_whenValidEntity_persistsSuccessfully() {
    ShortUrl url = new ShortUrl("abc123", "https://example.com/test", Instant.now(), true);
    ShortUrl saved = shortUrlRepository.save(url);

    assertThat(saved.getId()).isNotNull();
    assertThat(saved.getCode()).isEqualTo("abc123");
    assertThat(saved.getOriginalUrl()).isEqualTo("https://example.com/test");
    assertThat(saved.isActive()).isTrue();
  }

  @Test
  @DisplayName("Should find only active links when calling findByCodeAndActiveTrue")
  void findByCodeAndActiveTrue_returnsActiveUrlOnly() {
    Instant now = Instant.now();
    ShortUrl activeUrl = new ShortUrl("active1", "https://example.com/active", now, true);
    ShortUrl inactiveUrl = new ShortUrl("inactive1", "https://example.com/inactive", now, false);

    shortUrlRepository.save(activeUrl);
    shortUrlRepository.save(inactiveUrl);

    Optional<ShortUrl> foundActive = shortUrlRepository.findByCodeAndActiveTrue("active1");
    Optional<ShortUrl> foundInactive = shortUrlRepository.findByCodeAndActiveTrue("inactive1");

    assertThat(foundActive).isPresent();
    assertThat(foundActive.get().getOriginalUrl()).isEqualTo("https://example.com/active");
    assertThat(foundInactive).isEmpty();
  }

  @Test
  @DisplayName("Should throw DataIntegrityViolationException when duplicate short code is saved")
  void save_whenDuplicateCode_throwsDataIntegrityViolationException() {
    Instant now = Instant.now();
    ShortUrl url1 = new ShortUrl("dupCode", "https://example.com/first", now, true);
    ShortUrl url2 = new ShortUrl("dupCode", "https://example.com/second", now, true);

    shortUrlRepository.saveAndFlush(url1);

    assertThatThrownBy(() -> shortUrlRepository.saveAndFlush(url2))
        .isInstanceOf(DataIntegrityViolationException.class);
  }
}
