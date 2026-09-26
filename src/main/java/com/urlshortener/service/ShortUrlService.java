package com.urlshortener.service;

import com.urlshortener.domain.ShortUrl;
import com.urlshortener.dto.CreateShortUrlRequest;
import com.urlshortener.dto.ShortUrlResponse;
import com.urlshortener.exception.CodeGenerationException;
import com.urlshortener.exception.InvalidUrlException;
import com.urlshortener.exception.UrlNotFoundException;
import com.urlshortener.repository.ShortUrlRepository;
import java.net.URI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service for URL shortener business operations: validation, code generation with bounded retries,
 * and mapping.
 */
@Service
public class ShortUrlService {

  private static final Logger log = LoggerFactory.getLogger(ShortUrlService.class);
  private static final int MAX_COLLISION_RETRIES = 5;
  private static final int MAX_URL_LENGTH = 2048;

  private final ShortUrlRepository repository;
  private final ShortUrlCodeGenerator codeGenerator;
  private final String baseUrl;

  public ShortUrlService(
      ShortUrlRepository repository,
      ShortUrlCodeGenerator codeGenerator,
      @Value("${app.shortener.base-url:http://localhost:8080}") String baseUrl) {
    this.repository = repository;
    this.codeGenerator = codeGenerator;
    this.baseUrl = baseUrl.endsWith("/") ? baseUrl.substring(0, baseUrl.length() - 1) : baseUrl;
  }

  @Transactional
  public String getOriginalUrl(String code) {
    if (code == null || code.trim().isEmpty()) {
      throw new UrlNotFoundException("Short code must not be blank");
    }
    String cleanCode = code.trim();
    ShortUrl shortUrl =
        repository
            .findByCodeAndActiveTrue(cleanCode)
            .orElseThrow(() -> new UrlNotFoundException(cleanCode));

    try {
      repository.incrementClickCount(cleanCode, java.time.Instant.now());
    } catch (Exception e) {
      log.warn(
          "Best-effort click count increment failed for code '{}': {}", cleanCode, e.getMessage());
    }

    return shortUrl.getOriginalUrl();
  }

  @Transactional(readOnly = true)
  public ShortUrlResponse getShortUrlMetadata(String code) {
    if (code == null || code.trim().isEmpty()) {
      throw new UrlNotFoundException("Short code must not be blank");
    }
    return repository
        .findByCode(code.trim())
        .map(this::mapToResponse)
        .orElseThrow(() -> new UrlNotFoundException(code));
  }

  @Transactional(readOnly = true)
  public com.urlshortener.dto.ShortUrlAnalyticsResponse getAnalytics(String code) {
    if (code == null || code.trim().isEmpty()) {
      throw new UrlNotFoundException("Short code must not be blank");
    }
    return repository
        .findByCode(code.trim())
        .map(this::mapToAnalyticsResponse)
        .orElseThrow(() -> new UrlNotFoundException(code));
  }

  @Transactional
  public void deactivateShortUrl(String code) {
    if (code == null || code.trim().isEmpty()) {
      throw new UrlNotFoundException("Short code must not be blank");
    }
    ShortUrl shortUrl =
        repository.findByCode(code.trim()).orElseThrow(() -> new UrlNotFoundException(code));

    if (shortUrl.isActive()) {
      shortUrl.setActive(false);
      repository.save(shortUrl);
      log.info("Deactivated short URL for code '{}'", code);
    } else {
      log.info("Short URL for code '{}' is already inactive", code);
    }
  }

  @Transactional
  public ShortUrlResponse createShortUrl(CreateShortUrlRequest request) {
    validateOriginalUrl(request != null ? request.getOriginalUrl() : null);

    String originalUrl = request.getOriginalUrl().trim();

    for (int attempt = 1; attempt <= MAX_COLLISION_RETRIES; attempt++) {
      String code = codeGenerator.generateCode();
      if (repository.existsByCode(code)) {
        log.warn(
            "Collision detected for code '{}' on attempt {}/{}",
            code,
            attempt,
            MAX_COLLISION_RETRIES);
        continue;
      }

      ShortUrl shortUrl = new ShortUrl(code, originalUrl);
      try {
        ShortUrl saved = repository.save(shortUrl);
        log.info(
            "Created short URL with code '{}' for original URL length {}",
            saved.getCode(),
            originalUrl.length());
        return mapToResponse(saved);
      } catch (DataIntegrityViolationException e) {
        log.warn(
            "Data integrity violation for code '{}' on attempt {}/{}",
            code,
            attempt,
            MAX_COLLISION_RETRIES);
      }
    }

    log.error("Failed to generate unique short URL code after {} retries", MAX_COLLISION_RETRIES);
    throw new CodeGenerationException(
        "Failed to generate a unique short code after maximum retries");
  }

  private void validateOriginalUrl(String originalUrl) {
    if (originalUrl == null || originalUrl.trim().isEmpty()) {
      throw new InvalidUrlException("Original URL must not be blank");
    }
    if (originalUrl.length() > MAX_URL_LENGTH) {
      throw new InvalidUrlException(
          "Original URL exceeds maximum permitted length of " + MAX_URL_LENGTH + " characters");
    }
    try {
      URI uri = new URI(originalUrl.trim());
      String scheme = uri.getScheme();
      if (scheme == null
          || (!scheme.equalsIgnoreCase("http") && !scheme.equalsIgnoreCase("https"))) {
        throw new InvalidUrlException("URL scheme must be http or https");
      }
      if (uri.getHost() == null || uri.getHost().trim().isEmpty()) {
        throw new InvalidUrlException("URL must contain a valid host component");
      }
    } catch (Exception e) {
      if (e instanceof InvalidUrlException invalidUrlException) {
        throw invalidUrlException;
      }
      throw new InvalidUrlException("Invalid URL syntax: " + e.getMessage());
    }
  }

  private ShortUrlResponse mapToResponse(ShortUrl entity) {
    String fullShortUrl = baseUrl + "/" + entity.getCode();
    java.time.OffsetDateTime createdAt =
        entity.getCreatedAt() != null
            ? entity.getCreatedAt().atOffset(java.time.ZoneOffset.UTC)
            : null;
    return new ShortUrlResponse(
        entity.getCode(), fullShortUrl, entity.getOriginalUrl(), entity.isActive(), createdAt);
  }

  private com.urlshortener.dto.ShortUrlAnalyticsResponse mapToAnalyticsResponse(ShortUrl entity) {
    String fullShortUrl = baseUrl + "/" + entity.getCode();
    java.time.OffsetDateTime createdAt =
        entity.getCreatedAt() != null
            ? entity.getCreatedAt().atOffset(java.time.ZoneOffset.UTC)
            : null;
    java.time.OffsetDateTime lastAccessedAt =
        entity.getLastAccessedAt() != null
            ? entity.getLastAccessedAt().atOffset(java.time.ZoneOffset.UTC)
            : null;
    return new com.urlshortener.dto.ShortUrlAnalyticsResponse(
        entity.getCode(),
        fullShortUrl,
        entity.getOriginalUrl(),
        entity.getClickCount(),
        createdAt,
        lastAccessedAt,
        entity.isActive());
  }
}
