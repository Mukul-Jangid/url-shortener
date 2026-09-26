package com.urlshortener.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.urlshortener.exception.GlobalExceptionHandler;
import com.urlshortener.exception.UrlNotFoundException;
import com.urlshortener.service.ShortUrlService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(RedirectController.class)
@Import(GlobalExceptionHandler.class)
class RedirectControllerTest {

  @Autowired private MockMvc mockMvc;

  @MockBean private ShortUrlService service;

  @Test
  void redirect_activeCode_returns302FoundWithLocationHeader() throws Exception {
    when(service.getOriginalUrl("abc1234")).thenReturn("https://example.com/target-destination");

    mockMvc
        .perform(get("/abc1234"))
        .andExpect(status().isFound())
        .andExpect(header().string("Location", "https://example.com/target-destination"));
  }

  @Test
  void redirect_unknownCode_returns404NotFoundProblemDetail() throws Exception {
    when(service.getOriginalUrl("unknown")).thenThrow(new UrlNotFoundException("unknown"));

    mockMvc
        .perform(get("/unknown"))
        .andExpect(status().isNotFound())
        .andExpect(jsonPath("$.title").value("Short URL Not Found"))
        .andExpect(jsonPath("$.detail").value("Short URL not found or inactive for code: unknown"));
  }
}
