# URL Shortener — AI-Assisted Engineering Exercise

AI-assisted URL Shortener service built with **Java 21**, **Spring Boot 3.3.4**, **Spring Data JPA**, **H2 In-Memory Database**, and **Springdoc OpenAPI / Swagger UI**.

[![Java 21](https://img.shields.io/badge/Java-21-orange.svg)](https://openjdk.org/projects/jdk/21/)
[![Spring Boot 3.3.4](https://img.shields.io/badge/Spring%20Boot-3.3.4-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![Swagger UI](https://img.shields.io/badge/Swagger--UI-OpenAPI%20v3-blue.svg)](http://localhost:8080/swagger-ui.html)

---

## Key Capabilities & Features

- **Base Package Root**: `com.urlshortener`
- **7-Character Base62 Short Codes**: High-capacity random generator ($3.52 \text{ trillion}$ theoretical codes) with bounded 5 collision retries.
- **RFC 7807 Problem Details**: Standardized structured error responses for `400 Bad Request`, `404 Not Found`, and `500 Internal Error`.
- **Soft Deactivation**: Idempotent link deactivation (`active = false`) preserving auditability and history.
- **Interactive Swagger Documentation**: Live API UI at `http://localhost:8080/swagger-ui.html`.

---

## Quick Start & Running

### Prerequisites
- JDK 21 (with `javac`)
- Maven wrapper (`./mvnw` or `mvnw.cmd` on Windows)

### Run Application
```bash
./mvnw spring-boot:run
```
The server starts at `http://localhost:8080`.

- **Swagger UI**: `http://localhost:8080/swagger-ui.html`
- **OpenAPI v3 JSON Spec**: `http://localhost:8080/v3/api-docs`
- **Actuator Health Check**: `http://localhost:8080/actuator/health`
- **H2 In-Memory Console**: `http://localhost:8080/h2-console` (JDBC URL: `jdbc:h2:mem:urlshortener`, username: `sa`, blank password)

---

## Complete API Usage Guide & Examples

### 1. Create a Short URL (`POST /api/v1/urls`)
Accepts a long URL payload, validates syntax/scheme (`http` or `https`), generates a 7-character Base62 code, and persists the short link.

**cURL Request:**
```bash
curl -X POST http://localhost:8080/api/v1/urls \
  -H "Content-Type: application/json" \
  -d '{"originalUrl": "https://example.com/very/long/path/to/resource"}'
```

**Success Response (`201 Created`):**
```json
{
  "code": "abc1234",
  "shortUrl": "http://localhost:8080/abc1234",
  "originalUrl": "https://example.com/very/long/path/to/resource",
  "active": true,
  "createdAt": "2026-09-26T21:40:00Z"
}
```

**Invalid Input Error Response (`400 Bad Request`):**
```json
{
  "type": "urn:problem:invalid-url",
  "title": "Invalid Target URL",
  "status": 400,
  "detail": "URL scheme must be http or https",
  "instance": "/api/v1/urls"
}
```

---

### 2. Follow Short URL Redirect (`GET /{code}`)
Unversioned public redirect endpoint that resolves an active short code and issues a temporary HTTP 302 redirect.

**cURL Request:**
```bash
curl -i http://localhost:8080/abc1234
```

**Success Response (`302 Found`):**
```http
HTTP/1.1 302 Found
Location: https://example.com/very/long/path/to/resource
```

**Missing or Inactive Link Error Response (`404 Not Found`):**
```json
{
  "type": "urn:problem:url-not-found",
  "title": "Short URL Not Found",
  "status": 404,
  "detail": "Short URL not found or inactive for code: abc1234",
  "instance": "/abc1234"
}
```

---

### 3. Get Short URL Metadata (`GET /api/v1/urls/{code}`)
Non-redirecting lookup endpoint returning full metadata about a short code. Operates for both active and deactivated links.

**cURL Request:**
```bash
curl http://localhost:8080/api/v1/urls/abc1234
```

**Success Response (`200 OK`):**
```json
{
  "code": "abc1234",
  "shortUrl": "http://localhost:8080/abc1234",
  "originalUrl": "https://example.com/very/long/path/to/resource",
  "active": true,
  "createdAt": "2026-09-26T21:40:00Z"
}
```

---

### 4. Deactivate a Short URL (`DELETE /api/v1/urls/{code}`)
Soft-deactivates an existing short URL record by setting `active = false`. Execution is idempotent.

**cURL Request:**
```bash
curl -i -X DELETE http://localhost:8080/api/v1/urls/abc1234
```

**Success Response (`204 No Content`):**
```http
HTTP/1.1 204 No Content
```

> **Note**: After deactivation:
> - `GET /abc1234` returns `404 Not Found`.
> - `GET /api/v1/urls/abc1234` metadata lookup returns `200 OK` with `"active": false`.

---

### 5. Get Short URL Click Analytics (`GET /api/v1/urls/{code}/analytics`)
Retrieves click analytics metrics for a short code, including total successful redirects executed and the timestamp of the most recent redirect access.

**cURL Request:**
```bash
curl http://localhost:8080/api/v1/urls/abc1234/analytics
```

**Success Response (`200 OK`):**
```json
{
  "code": "abc1234",
  "shortUrl": "http://localhost:8080/abc1234",
  "originalUrl": "https://example.com/very/long/path/to/resource",
  "clickCount": 42,
  "createdAt": "2026-09-26T21:40:00Z",
  "lastAccessedAt": "2026-09-26T22:30:00Z",
  "active": true
}
```

---

## Testing & Quality Gates

Run all unit tests, integration tests, and Spotless code formatting checks:
```bash
./mvnw clean test spotless:check
```

Automatically apply Google Java Format styling:
```bash
./mvnw spotless:apply
```

---

## Project Structure

```text
src/main/java/com/urlshortener/
  UrlShortenerApplication.java    Spring Boot application entry point
  controller/                    REST API controllers (UrlController, RedirectController)
  service/                       Business logic (ShortUrlService, ShortUrlCodeGenerator)
  repository/                    Spring Data JPA data access (ShortUrlRepository)
  domain/                        JPA entities (ShortUrl)
  dto/                           API request/response payloads (CreateShortUrlRequest, ShortUrlResponse)
  exception/                     Centralized RFC 7807 exception handlers (GlobalExceptionHandler)
docs/                            Architecture decisions, conventions, and task backlog
```

---

## Documentation Index

| Topic | Location |
|---|---|
| Architecture Overview | [docs/architecture/00-overview.md](docs/architecture/00-overview.md) |
| API Contracts & Design | [docs/architecture/03-api-design.md](docs/architecture/03-api-design.md) |
| Architecture Decisions (ADRs) | [docs/decisions/](docs/decisions/) |
| Task Board & Status | [docs/tasks/00-index.md](docs/tasks/00-index.md) |
| AI Collaboration Audit Logs | [docs/ai-work-log/entries/](docs/ai-work-log/entries/) |
| Code & Development Conventions | [docs/conventions/](docs/conventions/) |
