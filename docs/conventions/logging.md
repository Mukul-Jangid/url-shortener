# Logging

- Structured, one logger per class (`private static final Logger log = ...`).
- No PII or full URLs with potentially sensitive query parameters logged at INFO level.
- Correlation/trace id included in the log pattern (see `application.yml`) once request tracing
  is added — not yet wired in as of the Phase 0 scaffold.
