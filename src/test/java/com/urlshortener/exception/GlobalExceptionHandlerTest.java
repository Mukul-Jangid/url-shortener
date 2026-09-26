package com.urlshortener.exception;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.URI;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

class GlobalExceptionHandlerTest {

  private GlobalExceptionHandler exceptionHandler;

  @BeforeEach
  void setUp() {
    exceptionHandler = new GlobalExceptionHandler();
  }

  @Test
  @DisplayName("Should map UrlNotFoundException to 404 ProblemDetail")
  void handleUrlNotFound_returnsNotFoundProblemDetail() {
    UrlNotFoundException ex = new UrlNotFoundException("missing123");
    ProblemDetail problem = exceptionHandler.handleUrlNotFound(ex);

    assertThat(problem.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
    assertThat(problem.getTitle()).isEqualTo("Short URL Not Found");
    assertThat(problem.getType()).isEqualTo(URI.create("urn:problem:url-not-found"));
    assertThat(problem.getDetail()).contains("missing123");
  }

  @Test
  @DisplayName("Should map InvalidUrlException to 400 ProblemDetail")
  void handleInvalidUrl_returnsBadRequestProblemDetail() {
    InvalidUrlException ex = new InvalidUrlException("URL scheme must be http or https");
    ProblemDetail problem = exceptionHandler.handleInvalidUrl(ex);

    assertThat(problem.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    assertThat(problem.getTitle()).isEqualTo("Invalid Target URL");
    assertThat(problem.getType()).isEqualTo(URI.create("urn:problem:invalid-url"));
    assertThat(problem.getDetail()).isEqualTo("URL scheme must be http or https");
  }

  @Test
  @DisplayName("Should map CodeGenerationException to 500 ProblemDetail")
  void handleCodeGeneration_returnsInternalServerErrorProblemDetail() {
    CodeGenerationException ex = new CodeGenerationException("Exhausted retries");
    ProblemDetail problem = exceptionHandler.handleCodeGeneration(ex);

    assertThat(problem.getStatus()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR.value());
    assertThat(problem.getTitle()).isEqualTo("Short Code Generation Failed");
    assertThat(problem.getType()).isEqualTo(URI.create("urn:problem:code-generation-failed"));
  }

  @Test
  @DisplayName(
      "Should map unhandled Exception to 500 ProblemDetail without leaking raw stack trace")
  void handleUnhandledException_returnsGenericInternalServerErrorProblemDetail() {
    RuntimeException ex = new RuntimeException("Unexpected DB drop");
    ProblemDetail problem = exceptionHandler.handleUnhandledException(ex);

    assertThat(problem.getStatus()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR.value());
    assertThat(problem.getTitle()).isEqualTo("Internal Server Error");
    assertThat(problem.getType()).isEqualTo(URI.create("urn:problem:internal-error"));
    assertThat(problem.getDetail()).isEqualTo("An internal server error occurred.");
  }
}
