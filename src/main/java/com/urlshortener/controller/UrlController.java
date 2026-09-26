package com.urlshortener.controller;

import com.urlshortener.dto.CreateShortUrlRequest;
import com.urlshortener.dto.ShortUrlResponse;
import com.urlshortener.service.ShortUrlService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** REST Controller exposing URL shortener API endpoints. */
@Tag(name = "URL Management", description = "Endpoints for creating and managing short links")
@RestController
@RequestMapping("/api/v1/urls")
public class UrlController {

  private final ShortUrlService service;

  public UrlController(ShortUrlService service) {
    this.service = service;
  }

  @Operation(
      summary = "Create a short URL",
      description = "Generates a 7-character Base62 short link for the provided original URL.")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "201",
            description = "Short URL created successfully",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ShortUrlResponse.class))),
        @ApiResponse(
            responseCode = "400",
            description = "Invalid URL syntax or unsupported scheme",
            content =
                @Content(
                    mediaType = "application/problem+json",
                    schema = @Schema(implementation = ProblemDetail.class))),
        @ApiResponse(
            responseCode = "500",
            description = "Short code generation failed after retries",
            content =
                @Content(
                    mediaType = "application/problem+json",
                    schema = @Schema(implementation = ProblemDetail.class)))
      })
  @PostMapping
  public ResponseEntity<ShortUrlResponse> createShortUrl(
      @RequestBody CreateShortUrlRequest request) {
    ShortUrlResponse response = service.createShortUrl(request);
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }
}
