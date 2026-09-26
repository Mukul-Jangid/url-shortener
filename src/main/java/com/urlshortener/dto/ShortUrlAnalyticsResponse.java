package com.urlshortener.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.OffsetDateTime;

/** Response DTO returning complete click analytics, short code metadata, and access timestamps. */
@Schema(description = "Response payload containing short link click analytics")
public class ShortUrlAnalyticsResponse {

  @Schema(description = "7-character Base62 short code", example = "abc1234")
  private String code;

  @Schema(description = "Full short URL link", example = "http://localhost:8080/abc1234")
  private String shortUrl;

  @Schema(
      description = "Original target web address",
      example = "https://example.com/very/long/path")
  private String originalUrl;

  @Schema(
      description = "Total number of successful redirects executed for this short link",
      example = "42")
  private long clickCount;

  @Schema(description = "Timestamp when the short link was created")
  private OffsetDateTime createdAt;

  @Schema(
      description = "Timestamp of the most recent redirect access (null if link has 0 clicks)",
      example = "2026-09-26T22:30:00Z")
  private OffsetDateTime lastAccessedAt;

  @Schema(
      description = "Whether the short link is active and usable for redirection",
      example = "true")
  private boolean active;

  public ShortUrlAnalyticsResponse() {}

  public ShortUrlAnalyticsResponse(
      String code,
      String shortUrl,
      String originalUrl,
      long clickCount,
      OffsetDateTime createdAt,
      OffsetDateTime lastAccessedAt,
      boolean active) {
    this.code = code;
    this.shortUrl = shortUrl;
    this.originalUrl = originalUrl;
    this.clickCount = clickCount;
    this.createdAt = createdAt;
    this.lastAccessedAt = lastAccessedAt;
    this.active = active;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getShortUrl() {
    return shortUrl;
  }

  public void setShortUrl(String shortUrl) {
    this.shortUrl = shortUrl;
  }

  public String getOriginalUrl() {
    return originalUrl;
  }

  public void setOriginalUrl(String originalUrl) {
    this.originalUrl = originalUrl;
  }

  public long getClickCount() {
    return clickCount;
  }

  public void setClickCount(long clickCount) {
    this.clickCount = clickCount;
  }

  public OffsetDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(OffsetDateTime createdAt) {
    this.createdAt = createdAt;
  }

  public OffsetDateTime getLastAccessedAt() {
    return lastAccessedAt;
  }

  public void setLastAccessedAt(OffsetDateTime lastAccessedAt) {
    this.lastAccessedAt = lastAccessedAt;
  }

  public boolean isActive() {
    return active;
  }

  public void setActive(boolean active) {
    this.active = active;
  }
}
