package com.urlshortener.exception;

/** Exception thrown when a requested short URL code is not found or is inactive. */
public class UrlNotFoundException extends UrlShortenerException {

  public UrlNotFoundException(String code) {
    super("Short URL not found or inactive for code: " + code);
  }
}
