# Metrics and Observability Guidelines

This document defines mandatory rules for application metrics using Spring Boot Actuator and Micrometer.

## 1. MeterRegistry & Injection

Use Micrometer's `MeterRegistry` injected via constructor injection:

```java
@Service
public class UrlService {
  private final MeterRegistry meterRegistry;

  public UrlService(MeterRegistry meterRegistry) {
    this.meterRegistry = meterRegistry;
  }
}
```

## 2. Metric Naming Strategy

* **Format**: Lowercase, dot-separated naming with project prefix: `urlshortener.<domain>.<action>.<type>`.
* **Standard Metrics**:
  * `urlshortener.links.created.total` (Counter): Tracks total short links created.
  * `urlshortener.redirects.total` (Counter): Tracks total redirect requests processed.
  * `urlshortener.redirect.latency` (Timer): Measures response latency for `GET /{code}` redirects.

## 3. Tagging Rules (Cardinality Protection)

* **Low-Cardinality Tags ONLY**: Use bounded, predefined tag keys and values (e.g. `status`, `outcome`, `error_type`).
* **STRICT RULE — NO HIGH-CARDINALITY TAGS**:
  * **NEVER** use short code strings, raw long URLs, user IDs, or IP addresses as metric tags.
  * *Reason*: High-cardinality tag values cause memory leaks in the Micrometer meter registry and crash monitoring tools (Prometheus/Grafana).

### **Example**:
```java
// Correct: Low-cardinality tag
meterRegistry.counter("urlshortener.redirects.total", "outcome", "success").increment();

// Incorrect: High-cardinality tag (FORBIDDEN)
meterRegistry.counter("urlshortener.redirects.total", "code", shortCode).increment();
```
