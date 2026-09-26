package com.urlshortener.controller;

import com.urlshortener.service.ShortUrlService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.net.URI;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/** Public REST Controller handling the unversioned GET /{code} redirect route. */
@Tag(name = "URL Redirect", description = "Public short link redirect endpoint")
@RestController
public class RedirectController {

  private final ShortUrlService service;

  public RedirectController(ShortUrlService service) {
    this.service = service;
  }

  @Operation(
      summary = "Redirect to target URL",
      description =
          "Resolves an active short code and redirects the client browser to the original target URL via HTTP 302 Found.")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "302",
            description = "Found — Temporary redirect to original URL",
            headers =
                @Header(
                    name = "Location",
                    description = "Target original URL destination",
                    schema = @Schema(type = "string"))),
        @ApiResponse(
            responseCode = "404",
            description = "Short URL not found or inactive",
            content =
                @Content(
                    mediaType = "application/problem+json",
                    schema = @Schema(implementation = ProblemDetail.class)))
      })
  @GetMapping("/{code}")
  public ResponseEntity<Void> redirect(
      @Parameter(description = "7-character short code identifier", example = "abc1234")
          @PathVariable("code")
          String code) {
    String originalUrl = service.getOriginalUrl(code);
    return ResponseEntity.status(HttpStatus.FOUND).location(URI.create(originalUrl)).build();
  }
}
