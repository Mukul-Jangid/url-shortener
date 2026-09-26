package com.urlshortener.exception;

/** Exception thrown when an input URL has invalid syntax or format. */
public class InvalidUrlException extends UrlShortenerException {

  public InvalidUrlException(String message) {
    super(message);
  }
}
