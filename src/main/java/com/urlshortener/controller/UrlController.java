package com.urlshortener.controller;

import com.urlshortener.dto.CreateShortUrlRequest;
import com.urlshortener.dto.ShortUrlResponse;
import com.urlshortener.service.ShortUrlService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

  @Operation(
      summary = "Get short URL metadata",
      description = "Retrieves metadata details for an existing short code without redirecting.")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "Metadata retrieved successfully",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ShortUrlResponse.class))),
        @ApiResponse(
            responseCode = "404",
            description = "Short URL code not found",
            content =
                @Content(
                    mediaType = "application/problem+json",
                    schema = @Schema(implementation = ProblemDetail.class)))
      })
  @GetMapping("/{code}")
  public ResponseEntity<ShortUrlResponse> getShortUrlMetadata(
      @Parameter(description = "7-character short code identifier", example = "abc1234")
          @PathVariable("code")
          String code) {
    ShortUrlResponse response = service.getShortUrlMetadata(code);
    return ResponseEntity.ok(response);
  }

  @Operation(
      summary = "Get click analytics for a short URL",
      description =
          "Retrieves total redirect click count and last access timestamp for a short link code.")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "Analytics retrieved successfully",
            content =
                @Content(
                    mediaType = "application/json",
                    schema =
                        @Schema(
                            implementation =
                                com.urlshortener.dto.ShortUrlAnalyticsResponse.class))),
        @ApiResponse(
            responseCode = "404",
            description = "Short URL code not found",
            content =
                @Content(
                    mediaType = "application/problem+json",
                    schema = @Schema(implementation = ProblemDetail.class)))
      })
  @GetMapping("/{code}/analytics")
  public ResponseEntity<com.urlshortener.dto.ShortUrlAnalyticsResponse> getAnalytics(
      @Parameter(description = "7-character short code identifier", example = "abc1234")
          @PathVariable("code")
          String code) {
    com.urlshortener.dto.ShortUrlAnalyticsResponse response = service.getAnalytics(code);
    return ResponseEntity.ok(response);
  }

  @Operation(
      summary = "Deactivate a short URL",
      description =
          "Soft-deactivates an existing short code setting active=false (idempotent 204 response).")
  @ApiResponses(
      value = {
        @ApiResponse(responseCode = "204", description = "Deactivated successfully (no content)"),
        @ApiResponse(
            responseCode = "404",
            description = "Short URL code not found",
            content =
                @Content(
                    mediaType = "application/problem+json",
                    schema = @Schema(implementation = ProblemDetail.class)))
      })
  @org.springframework.web.bind.annotation.DeleteMapping("/{code}")
  public ResponseEntity<Void> deactivateShortUrl(
      @Parameter(description = "7-character short code identifier", example = "abc1234")
          @PathVariable("code")
          String code) {
    service.deactivateShortUrl(code);
    return ResponseEntity.noContent().build();
  }
}
