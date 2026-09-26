package com.urlshortener.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.urlshortener.domain.ShortUrl;
import com.urlshortener.repository.ShortUrlRepository;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class UrlAnalyticsConcurrencyTest {

  @Autowired private MockMvc mockMvc;

  @Autowired private ShortUrlRepository repository;

  @Test
  void concurrentRedirects_incrementClickCountAtomicallyWithoutLostUpdates() throws Exception {
    String code = "conc123";
    String targetUrl = "https://example.com/concurrent-test";
    repository.save(new ShortUrl(code, targetUrl));

    int threadCount = 20;
    ExecutorService executor = Executors.newFixedThreadPool(threadCount);
    CountDownLatch startLatch = new CountDownLatch(1);
    CountDownLatch finishLatch = new CountDownLatch(threadCount);
    AtomicInteger successfulRedirects = new AtomicInteger(0);

    for (int i = 0; i < threadCount; i++) {
      executor.submit(
          () -> {
            try {
              startLatch.await();
              mockMvc
                  .perform(get("/" + code))
                  .andExpect(status().isFound())
                  .andExpect(header().string("Location", targetUrl));
              successfulRedirects.incrementAndGet();
            } catch (Exception e) {
              e.printStackTrace();
            } finally {
              finishLatch.countDown();
            }
          });
    }

    startLatch.countDown();
    boolean completed = finishLatch.await(10, TimeUnit.SECONDS);
    executor.shutdown();

    assertEquals(true, completed, "All concurrent requests should finish within timeout");
    assertEquals(threadCount, successfulRedirects.get(), "All 20 redirects should return HTTP 302");

    ShortUrl updatedEntity = repository.findByCode(code).orElseThrow();
    assertEquals(
        threadCount,
        updatedEntity.getClickCount(),
        "Database click count must equal exactly 20 without lost updates");
  }
}
