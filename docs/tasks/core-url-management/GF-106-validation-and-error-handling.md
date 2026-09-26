# [GF-106] Input validation + centralized error handling

| Field | Value |
|---|---|
| **Type** | Task |
| **Module** | Core URL Management |
| **Epic / Phase** | Phase 1 — Greenfield Core |
| **Status** | In Review |
| **Priority** | High |
| **Depends on** | GF-101 |
| **Blocks** | GF-102 |
| **AI Work Log** | docs/ai-work-log/entries/GF-106.md |

## Summary

Cross-cutting: Bean Validation on request DTOs + a single `@ControllerAdvice` mapping every
domain exception to an RFC 7807 `ProblemDetail` response, per
`docs/conventions/error-handling.md`.

## Acceptance Criteria

- [x] Define basic URL validation for the create request: nonblank, parseable absolute
      HTTP/HTTPS URL with a host. Choose and document a length limit before implementation.
- [x] Define stable error categories for invalid input, missing codes, and exhausted creation
      retries; add focused tests for the shared validation and error mapping
- [x] A single `@ControllerAdvice` in `exception/` handles all domain exceptions — no
      controller has its own try/catch for business exceptions
- [x] Validation failures return `400` with a `ProblemDetail` body, not a raw stack trace
- [x] Provide the shared missing-code exception/mapping that subsequent endpoints will use;
      verify actual endpoint errors in their owning feature tasks

## Technical Notes / Constraints

- Establish a minimal shared foundation before endpoints. Do not build speculative exception
  hierarchies or endpoint code here. Feature tasks add their DTO constraints and integration
  tests using this foundation. No DNS resolution, remote fetch, or destination reputation checks.

## AI Collaboration Plan

- **Intent**: Centralize error handling; add request validation.
- **Constraints**: One `@ControllerAdvice` only; no per-controller try/catch for business errors.
- **Acceptance criteria**: as listed above.
- **Technical context**: `docs/conventions/error-handling.md`, the planned GF-102/GF-103 contracts (endpoints do not exist yet).

## Definition of Done

- [x] Code implemented per acceptance criteria
- [x] Tests updated to assert `ProblemDetail` shape on error paths
- [x] Quality gates passed
- [x] AI Work Log entry closed

## Dev Notes

Implemented domain exception hierarchy under `com.urlshortener.exception`:
- `UrlShortenerException` (base runtime exception)
- `UrlNotFoundException` (404 Not Found)
- `InvalidUrlException` (400 Bad Request)
- `CodeGenerationException` (500 Internal Server Error)
Implemented `GlobalExceptionHandler` annotated with `@RestControllerAdvice`:
- Maps domain exceptions and Spring `MethodArgumentNotValidException` to RFC 7807 `ProblemDetail` responses.
- Uses stable problem type URIs (`urn:problem:url-not-found`, `urn:problem:invalid-url`, `urn:problem:validation-error`, `urn:problem:code-generation-failed`, `urn:problem:internal-error`).
- Unhandled exceptions logged at `ERROR` level with stack trace; clients receive a clean 500 `ProblemDetail`.
Wrote unit tests in `GlobalExceptionHandlerTest` verifying HTTP status codes, titles, types, and details.
Ran `./mvnw clean test spotless:check`: 8/8 tests passed cleanly, 18 Java files clean.

## Related

- —
