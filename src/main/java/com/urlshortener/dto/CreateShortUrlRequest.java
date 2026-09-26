package com.urlshortener.dto;

import io.swagger.v3.oas.annotations.media.Schema;

/** Request DTO containing the target original URL to be shortened. */
@Schema(description = "Payload for creating a shortened URL")
public class CreateShortUrlRequest {

  @Schema(
      description = "The target original web address to shorten",
      example = "https://example.com/very/long/path")
  private String originalUrl;

  public CreateShortUrlRequest() {}

  public CreateShortUrlRequest(String originalUrl) {
    this.originalUrl = originalUrl;
  }

  public String getOriginalUrl() {
    return originalUrl;
  }

  public void setOriginalUrl(String originalUrl) {
    this.originalUrl = originalUrl;
  }
}
