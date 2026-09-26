package com.urlshortener.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.OffsetDateTime;

/**
 * Response DTO returning short code, full short URL, target original URL, active status, and
 * creation time.
 */
@Schema(description = "Response payload containing short link metadata")
public class ShortUrlResponse {

  @Schema(description = "7-character Base62 short code", example = "abc1234")
  private String code;

  @Schema(description = "Full short URL link", example = "http://localhost:8080/abc1234")
  private String shortUrl;

  @Schema(
      description = "Original target web address",
      example = "https://example.com/very/long/path")
  private String originalUrl;

  @Schema(
      description = "Whether the short link is active and usable for redirection",
      example = "true")
  private boolean active;

  @Schema(description = "Timestamp when the short link was created")
  private OffsetDateTime createdAt;

  public ShortUrlResponse() {}

  public ShortUrlResponse(
      String code, String shortUrl, String originalUrl, boolean active, OffsetDateTime createdAt) {
    this.code = code;
    this.shortUrl = shortUrl;
    this.originalUrl = originalUrl;
    this.active = active;
    this.createdAt = createdAt;
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

  public boolean isActive() {
    return active;
  }

  public void setActive(boolean active) {
    this.active = active;
  }

  public OffsetDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(OffsetDateTime createdAt) {
    this.createdAt = createdAt;
  }
}
