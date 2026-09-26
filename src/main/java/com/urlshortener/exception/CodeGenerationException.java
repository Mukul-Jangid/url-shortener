package com.urlshortener.exception;

/** Exception thrown when unique short code generation fails after retries. */
public class CodeGenerationException extends UrlShortenerException {

  public CodeGenerationException(String message) {
    super(message);
  }

  public CodeGenerationException(String message, Throwable cause) {
    super(message, cause);
  }
}
