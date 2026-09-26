package com.urlshortener.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.urlshortener.dto.CreateShortUrlRequest;
import com.urlshortener.dto.ShortUrlResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@SpringBootTest
@AutoConfigureMockMvc
class DeactivateAndRedirectIntegrationTest {

  @Autowired private MockMvc mockMvc;

  @Autowired private ObjectMapper objectMapper;

  @Test
  void createDeactivateAndVerifyRedirectAndMetadata() throws Exception {
    // 1. Create short URL
    CreateShortUrlRequest createRequest = new CreateShortUrlRequest("https://example.com/dest");
    MvcResult createResult =
        mockMvc
            .perform(
                post("/api/v1/urls")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(createRequest)))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.active").value(true))
            .andReturn();

    String content = createResult.getResponse().getContentAsString();
    ShortUrlResponse createResponse = objectMapper.readValue(content, ShortUrlResponse.class);
    String code = createResponse.getCode();

    // 2. Redirect before deactivation -> 302 Found
    mockMvc
        .perform(get("/" + code))
        .andExpect(status().isFound())
        .andExpect(header().string("Location", "https://example.com/dest"));

    // 3. Deactivate short URL -> 204 No Content
    mockMvc.perform(delete("/api/v1/urls/" + code)).andExpect(status().isNoContent());

    // 4. Redirect after deactivation -> 404 Not Found
    mockMvc
        .perform(get("/" + code))
        .andExpect(status().isNotFound())
        .andExpect(jsonPath("$.title").value("Short URL Not Found"));

    // 5. Metadata lookup after deactivation -> 200 OK with active=false
    mockMvc
        .perform(get("/api/v1/urls/" + code))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.code").value(code))
        .andExpect(jsonPath("$.active").value(false));
  }
}
