package com.urlshortener.exception;

import java.net.URI;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Centralized controller advice that maps domain exceptions to RFC 7807 ProblemDetail responses.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

  private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

  /** Handles missing or inactive short URL lookups. */
  @ExceptionHandler(UrlNotFoundException.class)
  public ProblemDetail handleUrlNotFound(UrlNotFoundException ex) {
    log.warn("Resource not found: {}", ex.getMessage());
    ProblemDetail problemDetail =
        ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
    problemDetail.setTitle("Short URL Not Found");
    problemDetail.setType(URI.create("urn:problem:url-not-found"));
    return problemDetail;
  }

  /** Handles malformed URL syntax errors. */
  @ExceptionHandler(InvalidUrlException.class)
  public ProblemDetail handleInvalidUrl(InvalidUrlException ex) {
    log.warn("Invalid input URL: {}", ex.getMessage());
    ProblemDetail problemDetail =
        ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
    problemDetail.setTitle("Invalid Target URL");
    problemDetail.setType(URI.create("urn:problem:invalid-url"));
    return problemDetail;
  }

  /** Handles Bean Validation failures on DTO request bodies. */
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ProblemDetail handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
    String detailMessage =
        ex.getBindingResult().getFieldErrors().stream()
            .map(error -> error.getField() + ": " + error.getDefaultMessage())
            .collect(Collectors.joining(", "));

    log.warn("Request validation failed: {}", detailMessage);
    ProblemDetail problemDetail =
        ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, detailMessage);
    problemDetail.setTitle("Validation Error");
    problemDetail.setType(URI.create("urn:problem:validation-error"));
    return problemDetail;
  }

  /** Handles code generation failure when retries are exhausted. */
  @ExceptionHandler(CodeGenerationException.class)
  public ProblemDetail handleCodeGeneration(CodeGenerationException ex) {
    log.error("Code generation error: {}", ex.getMessage(), ex);
    ProblemDetail problemDetail =
        ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
    problemDetail.setTitle("Short Code Generation Failed");
    problemDetail.setType(URI.create("urn:problem:code-generation-failed"));
    return problemDetail;
  }

  /** Catch-all handler for unhandled internal server errors. */
  @ExceptionHandler(Exception.class)
  public ProblemDetail handleUnhandledException(Exception ex) {
    log.error("Unhandled internal server error occurred", ex);
    ProblemDetail problemDetail =
        ProblemDetail.forStatusAndDetail(
            HttpStatus.INTERNAL_SERVER_ERROR, "An internal server error occurred.");
    problemDetail.setTitle("Internal Server Error");
    problemDetail.setType(URI.create("urn:problem:internal-error"));
    return problemDetail;
  }
}
