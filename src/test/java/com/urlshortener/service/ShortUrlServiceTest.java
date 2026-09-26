package com.urlshortener.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.urlshortener.domain.ShortUrl;
import com.urlshortener.dto.CreateShortUrlRequest;
import com.urlshortener.dto.ShortUrlResponse;
import com.urlshortener.exception.CodeGenerationException;
import com.urlshortener.exception.InvalidUrlException;
import com.urlshortener.repository.ShortUrlRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ShortUrlServiceTest {

  private ShortUrlRepository repository;
  private ShortUrlCodeGenerator codeGenerator;
  private ShortUrlService service;

  @BeforeEach
  void setUp() {
    repository = mock(ShortUrlRepository.class);
    codeGenerator = mock(ShortUrlCodeGenerator.class);
    service = new ShortUrlService(repository, codeGenerator, "http://localhost:8080");
  }

  @Test
  void createShortUrl_validUrl_returnsShortUrlResponse() {
    when(codeGenerator.generateCode()).thenReturn("abc1234");
    when(repository.existsByCode("abc1234")).thenReturn(false);
    when(repository.save(any(ShortUrl.class))).thenAnswer(invocation -> invocation.getArgument(0));

    CreateShortUrlRequest request = new CreateShortUrlRequest("https://example.com/long/path");
    ShortUrlResponse response = service.createShortUrl(request);

    assertNotNull(response);
    assertEquals("abc1234", response.getCode());
    assertEquals("http://localhost:8080/abc1234", response.getShortUrl());
    assertEquals("https://example.com/long/path", response.getOriginalUrl());
    assertTrue(response.isActive());
    assertNotNull(response.getCreatedAt());
  }

  @Test
  void createShortUrl_invalidUrl_throwsInvalidUrlException() {
    CreateShortUrlRequest emptyRequest = new CreateShortUrlRequest(" ");
    assertThrows(InvalidUrlException.class, () -> service.createShortUrl(emptyRequest));

    CreateShortUrlRequest ftpRequest = new CreateShortUrlRequest("ftp://example.com");
    assertThrows(InvalidUrlException.class, () -> service.createShortUrl(ftpRequest));
  }

  @Test
  void createShortUrl_collisionRetries_succeedsOnLaterAttempt() {
    when(codeGenerator.generateCode()).thenReturn("code1", "code2");
    when(repository.existsByCode("code1")).thenReturn(true);
    when(repository.existsByCode("code2")).thenReturn(false);
    when(repository.save(any(ShortUrl.class))).thenAnswer(i -> i.getArgument(0));

    CreateShortUrlRequest request = new CreateShortUrlRequest("https://example.com");
    ShortUrlResponse response = service.createShortUrl(request);

    assertEquals("code2", response.getCode());
    verify(codeGenerator, times(2)).generateCode();
  }

  @Test
  void createShortUrl_exceedsMaxRetries_throwsCodeGenerationException() {
    when(codeGenerator.generateCode()).thenReturn("dupCode");
    when(repository.existsByCode("dupCode")).thenReturn(true);

    CreateShortUrlRequest request = new CreateShortUrlRequest("https://example.com");
    assertThrows(CodeGenerationException.class, () -> service.createShortUrl(request));
    verify(codeGenerator, times(5)).generateCode();
  }

  @Test
  void getOriginalUrl_activeCode_returnsOriginalUrl() {
    ShortUrl shortUrl = new ShortUrl("active1", "https://example.com/target");
    when(repository.findByCodeAndActiveTrue("active1")).thenReturn(java.util.Optional.of(shortUrl));

    String originalUrl = service.getOriginalUrl("active1");
    assertEquals("https://example.com/target", originalUrl);
  }

  @Test
  void getOriginalUrl_unknownOrInactiveCode_throwsUrlNotFoundException() {
    when(repository.findByCodeAndActiveTrue("unknown")).thenReturn(java.util.Optional.empty());

    assertThrows(
        com.urlshortener.exception.UrlNotFoundException.class,
        () -> service.getOriginalUrl("unknown"));
  }

  @Test
  void getShortUrlMetadata_existingCode_returnsResponseDto() {
    ShortUrl shortUrl = new ShortUrl("meta123", "https://example.com/meta");
    when(repository.findByCode("meta123")).thenReturn(java.util.Optional.of(shortUrl));

    ShortUrlResponse response = service.getShortUrlMetadata("meta123");
    assertNotNull(response);
    assertEquals("meta123", response.getCode());
    assertEquals("https://example.com/meta", response.getOriginalUrl());
    assertEquals("http://localhost:8080/meta123", response.getShortUrl());
    assertTrue(response.isActive());
  }

  @Test
  void getShortUrlMetadata_unknownCode_throwsUrlNotFoundException() {
    when(repository.findByCode("missing")).thenReturn(java.util.Optional.empty());

    assertThrows(
        com.urlshortener.exception.UrlNotFoundException.class,
        () -> service.getShortUrlMetadata("missing"));
  }

  @Test
  void deactivateShortUrl_activeCode_setsActiveFalse() {
    ShortUrl shortUrl = new ShortUrl("codeDeact", "https://example.com");
    assertTrue(shortUrl.isActive());
    when(repository.findByCode("codeDeact")).thenReturn(java.util.Optional.of(shortUrl));

    service.deactivateShortUrl("codeDeact");

    org.junit.jupiter.api.Assertions.assertFalse(shortUrl.isActive());
    verify(repository, times(1)).save(shortUrl);
  }

  @Test
  void deactivateShortUrl_alreadyInactive_idempotentNoAction() {
    ShortUrl shortUrl = new ShortUrl("codeDeact", "https://example.com");
    shortUrl.setActive(false);
    when(repository.findByCode("codeDeact")).thenReturn(java.util.Optional.of(shortUrl));

    service.deactivateShortUrl("codeDeact");

    org.junit.jupiter.api.Assertions.assertFalse(shortUrl.isActive());
    verify(repository, times(0)).save(shortUrl);
  }

  @Test
  void deactivateShortUrl_unknownCode_throwsUrlNotFoundException() {
    when(repository.findByCode("missing")).thenReturn(java.util.Optional.empty());

    assertThrows(
        com.urlshortener.exception.UrlNotFoundException.class,
        () -> service.deactivateShortUrl("missing"));
  }
}
