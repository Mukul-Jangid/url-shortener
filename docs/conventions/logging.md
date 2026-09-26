# Logging Guidelines

This document defines mandatory logging rules for all code in this repository. Both AI agents and developers must strictly follow these rules when adding or modifying log statements.

## 1. Logger Declaration

Use SLF4J `LoggerFactory` with standard class logger instance:

```java
private static final Logger log = LoggerFactory.getLogger(ShortUrlService.class);
```

* One logger per class.
* Declare as `private static final`.
* Name variable `log`.

## 2. Log Levels & Usage Rules

### **INFO Level (Key State & Business Events)**
Use `INFO` only for significant business lifecycle events. Keep messages concise and low volume.
* **When to use**: Application startup/shutdown, successfully creating a new short link.
* **Example**:
  ```java
  log.info("Created short URL link with code={}", shortUrl.getCode());
  ```

### **WARN Level (Expected Operational Errors & Client Failures)**
Use `WARN` for client-side errors, missing resources, or retry attempts that do not crash the system.
* **When to use**: Short link lookup 404 (not found), deactivated short link accessed, validation failure 400, short code generation collision retry.
* **Example**:
  ```java
  log.warn("Short URL lookup failed: code={} not found or inactive", code);
  ```

### **ERROR Level (System Failures & Unexpected Exceptions)**
Use `ERROR` for unexpected system failures, database outages, or 500 internal errors that require developer investigation.
* **When to use**: Database connection failures, unhandled runtime exceptions in `@RestControllerAdvice`.
* **Rule**: Always pass the `Throwable` exception object as the last argument so the stack trace is captured.
* **Example**:
  ```java
  log.error("Unhandled exception processing request for path={}", request.getRequestURI(), ex);
  ```

### **DEBUG Level (Developer Diagnostics)**
Use `DEBUG` for detailed step-by-step diagnostic context needed during local development.
* **When to use**: Code generation retry details, input normalization steps.
* **Example**:
  ```java
  log.debug("Normalizing target URL before persistence: rawUrl={}", rawUrl);
  ```

## 3. Formatting & Performance Rules

1. **Use SLF4J Placeholders `{}`**:
   * Never use String concatenation (`+`) in log statements.
   * **Correct**: `log.info("Found URL with id={}", id);`
   * **Incorrect**: `log.info("Found URL with id=" + id);`
2. **Plain and Simple Language**:
   * Write clear, direct log messages. Avoid complex jargon or obscure abbreviations.
3. **No Redundant Logging**:
   * Do not log the same exception multiple times (e.g. log in service layer AND re-log in controller layer). Log at the boundary where the error is handled or mapped.

## 4. Security & Privacy Rules (What NOT to Log)

* **NO Sensitive Credentials**: Never log passwords, API keys, tokens, or authorization headers.
* **NO Raw PII**: Never log personal user information or sensitive query parameters.
* **Truncate Long URLs**: If logging target URLs, sanitize or truncate query strings to avoid leaking sensitive tokens embedded in third-party redirect URLs.
