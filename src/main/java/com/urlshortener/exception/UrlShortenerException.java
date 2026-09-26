package com.urlshortener.exception;

/** Base runtime exception for all URL shortener domain errors. */
public abstract class UrlShortenerException extends RuntimeException {

  protected UrlShortenerException(String message) {
    super(message);
  }

  protected UrlShortenerException(String message, Throwable cause) {
    super(message, cause);
  }
}
