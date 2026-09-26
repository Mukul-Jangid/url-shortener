package com.urlshortener.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.urlshortener.dto.CreateShortUrlRequest;
import com.urlshortener.dto.ShortUrlResponse;
import com.urlshortener.exception.GlobalExceptionHandler;
import com.urlshortener.exception.InvalidUrlException;
import com.urlshortener.exception.UrlNotFoundException;
import com.urlshortener.service.ShortUrlService;
import java.time.OffsetDateTime;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(UrlController.class)
@Import(GlobalExceptionHandler.class)
class UrlControllerTest {

  @Autowired private MockMvc mockMvc;

  @Autowired private ObjectMapper objectMapper;

  @MockBean private ShortUrlService service;

  @Test
  void createShortUrl_validPayload_returns201Created() throws Exception {
    ShortUrlResponse response =
        new ShortUrlResponse(
            "abcdef7",
            "http://localhost:8080/abcdef7",
            "https://example.com/test",
            true,
            OffsetDateTime.now());

    when(service.createShortUrl(any(CreateShortUrlRequest.class))).thenReturn(response);

    CreateShortUrlRequest request = new CreateShortUrlRequest("https://example.com/test");

    mockMvc
        .perform(
            post("/api/v1/urls")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.code").value("abcdef7"))
        .andExpect(jsonPath("$.shortUrl").value("http://localhost:8080/abcdef7"))
        .andExpect(jsonPath("$.originalUrl").value("https://example.com/test"))
        .andExpect(jsonPath("$.active").value(true));
  }

  @Test
  void createShortUrl_invalidUrl_returns400BadRequestProblemDetail() throws Exception {
    when(service.createShortUrl(any(CreateShortUrlRequest.class)))
        .thenThrow(new InvalidUrlException("URL scheme must be http or https"));

    CreateShortUrlRequest request = new CreateShortUrlRequest("ftp://example.com");

    mockMvc
        .perform(
            post("/api/v1/urls")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.title").value("Invalid Target URL"))
        .andExpect(jsonPath("$.detail").value("URL scheme must be http or https"));
  }

  @Test
  void getShortUrlMetadata_existingCode_returns200OkWithResponseDto() throws Exception {
    ShortUrlResponse response =
        new ShortUrlResponse(
            "abcdef7",
            "http://localhost:8080/abcdef7",
            "https://example.com/test",
            true,
            OffsetDateTime.now());

    when(service.getShortUrlMetadata("abcdef7")).thenReturn(response);

    mockMvc
        .perform(get("/api/v1/urls/abcdef7"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.code").value("abcdef7"))
        .andExpect(jsonPath("$.shortUrl").value("http://localhost:8080/abcdef7"))
        .andExpect(jsonPath("$.originalUrl").value("https://example.com/test"))
        .andExpect(jsonPath("$.active").value(true));
  }

  @Test
  void getShortUrlMetadata_unknownCode_returns404NotFoundProblemDetail() throws Exception {
    when(service.getShortUrlMetadata("missing")).thenThrow(new UrlNotFoundException("missing"));

    mockMvc
        .perform(get("/api/v1/urls/missing"))
        .andExpect(status().isNotFound())
        .andExpect(jsonPath("$.title").value("Short URL Not Found"))
        .andExpect(jsonPath("$.detail").value("Short URL not found or inactive for code: missing"));
  }
}
