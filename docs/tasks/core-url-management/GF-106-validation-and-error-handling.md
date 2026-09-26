# [GF-106] Input validation + centralized error handling

| Field | Value |
|---|---|
| **Type** | Task |
| **Module** | Core URL Management |
| **Epic / Phase** | Phase 1 — Greenfield Core |
| **Status** | Not Started |
| **Priority** | High |
| **Depends on** | GF-102, GF-103 |
| **Blocks** | GF-107 |
| **AI Work Log** | docs/ai-work-log/entries/GF-106.md |

## Summary

Cross-cutting: Bean Validation on request DTOs + a single `@ControllerAdvice` mapping every
domain exception to an RFC 7807 `ProblemDetail` response, per
`docs/conventions/error-handling.md`.

## Acceptance Criteria

- [ ] All request DTOs use `jakarta.validation` annotations (`@NotBlank`, etc.) with meaningful
      messages
- [ ] A single `@ControllerAdvice` in `exception/` handles all domain exceptions — no
      controller has its own try/catch for business exceptions
- [ ] Validation failures return `400` with a `ProblemDetail` body, not a raw stack trace
- [ ] Unknown-code lookups (already implemented per-endpoint in GF-103/104/105) are confirmed to
      route through the same centralized exception type (`UrlNotFoundException` or similar), not
      duplicated per-controller logic

## Technical Notes / Constraints

- This task is partly a refactor of GF-102/103/104/105's error handling into one place — expect
  to touch all four controllers to remove ad hoc error handling in favor of thrown exceptions
  caught centrally.

## AI Collaboration Plan

- **Intent**: Centralize error handling; add request validation.
- **Constraints**: One `@ControllerAdvice` only; no per-controller try/catch for business errors.
- **Acceptance criteria**: as listed above.
- **Technical context**: `docs/conventions/error-handling.md`, all of GF-102 through GF-105.

## Definition of Done

- [ ] Code implemented per acceptance criteria
- [ ] Tests updated to assert `ProblemDetail` shape on error paths
- [ ] Quality gates passed
- [ ] AI Work Log entry closed

## Dev Notes

*(fill in once complete)*

## Related

- —
