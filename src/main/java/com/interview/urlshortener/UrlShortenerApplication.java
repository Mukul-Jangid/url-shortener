package com.interview.urlshortener;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Entry point for the URL Shortener service.
 *
 * <p>Scenario/task provenance: SCAFFOLD-000 (see docs/TASKS.md). This class is intentionally
 * empty of business logic — it exists only so the project builds and runs before any
 * greenfield/brownfield/ambiguous scenario work begins, per the "guardrails before agent work"
 * setup documented in docs/00-INDEX.md.
 */
@SpringBootApplication
public class UrlShortenerApplication {

    public static void main(String[] args) {
        SpringApplication.run(UrlShortenerApplication.class, args);
    }
}
