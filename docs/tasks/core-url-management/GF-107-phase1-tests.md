# [GF-107] Unit + integration tests, Phase 1

| Field | Value |
|---|---|
| **Type** | Task |
| **Module** | Core URL Management |
| **Epic / Phase** | Phase 1 — Greenfield Core |
| **Status** | In Review |
| **Priority** | High |
| **Depends on** | GF-102, GF-103, GF-104, GF-105, GF-106 |
| **Blocks** | Phase 1 completion (gates BF-201, AMB-301, DOC-501) |
| **AI Work Log** | docs/ai-work-log/entries/GF-107.md |

## Summary

Audits Phase 1 testing: this task is a checkpoint, not new feature work — confirms
Phase 1 endpoints have integration coverage and meaningful service rules have unit coverage
per `docs/conventions/testing.md`, and that coverage isn't just happy-path.

## Acceptance Criteria

- [x] Every Phase 1 endpoint has at least one happy-path and one error-path integration test
- [x] Meaningful service business rules have focused unit tests; do not duplicate trivial
      delegation already covered by integration tests
- [x] Collision-retry-bound behavior (GF-102) has a dedicated test
- [x] Idempotency of deactivation (GF-105) has a dedicated test
- [x] `mvn test` is green with no skipped/ignored tests

## AI Collaboration Plan

- **Intent**: Fill any coverage gaps left across GF-102 through GF-106.
- **Constraints**: No test should assert on implementation details (e.g., exact exception
  message text) where it would make BF-202's later refactor brittle — assert on HTTP status /
  `ProblemDetail` `type` fields instead.
- **Acceptance criteria**: as listed above.
- **Technical context**: all of Phase 1's task files, `docs/conventions/testing.md`.

## Definition of Done

- [x] Coverage gaps closed
- [x] Quality gates passed
- [x] AI Work Log entry closed
- [x] `docs/tasks/00-index.md` board updated to reflect Phase 1 complete

## Dev Notes

*(fill in once complete)*

## Related

- —
