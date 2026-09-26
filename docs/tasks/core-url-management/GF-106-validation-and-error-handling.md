# [GF-106] Input validation + centralized error handling

| Field | Value |
|---|---|
| **Type** | Task |
| **Module** | Core URL Management |
| **Epic / Phase** | Phase 1 — Greenfield Core |
| **Status** | Not Started |
| **Priority** | High |
| **Depends on** | GF-101 |
| **Blocks** | GF-102 |
| **AI Work Log** | docs/ai-work-log/entries/GF-106.md |

## Summary

Cross-cutting: Bean Validation on request DTOs + a single `@ControllerAdvice` mapping every
domain exception to an RFC 7807 `ProblemDetail` response, per
`docs/conventions/error-handling.md`.

## Acceptance Criteria

- [ ] Define basic URL validation for the create request: nonblank, parseable absolute
      HTTP/HTTPS URL with a host. Choose and document a length limit before implementation.
- [ ] Define stable error categories for invalid input, missing codes, and exhausted creation
      retries; add focused tests for the shared validation and error mapping
- [ ] A single `@ControllerAdvice` in `exception/` handles all domain exceptions — no
      controller has its own try/catch for business exceptions
- [ ] Validation failures return `400` with a `ProblemDetail` body, not a raw stack trace
- [ ] Provide the shared missing-code exception/mapping that subsequent endpoints will use;
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

- [ ] Code implemented per acceptance criteria
- [ ] Tests updated to assert `ProblemDetail` shape on error paths
- [ ] Quality gates passed
- [ ] AI Work Log entry closed

## Dev Notes

*(fill in once complete)*

## Related

- —
