# [VAL-402] Rate limiting on create endpoint

| Field | Value |
|---|---|
| **Type** | Story |
| **Module** | Validation & Hardening |
| **Epic / Phase** | Phase 4 — Validation |
| **Status** | Not Started |
| **Priority** | Medium |
| **Depends on** | VAL-401 |
| **Blocks** | — |
| **AI Work Log** | docs/ai-work-log/entries/VAL-402.md |

## Summary

Mitigate R-007 (abuse via unrestricted link creation) with a rate limiter on
`POST /api/v1/urls`.

## Acceptance Criteria

- [ ] Requests exceeding the configured rate return `429` with a `ProblemDetail` body
- [ ] Limit is configurable (not hardcoded magic number buried in logic)
- [ ] Rate limiting is scoped to the create endpoint only — does not affect redirect (`GF-103`)
      or lookup (`GF-104`) endpoints

## AI Collaboration Plan

- **Intent**: Add a rate limiter to one endpoint only.
- **Constraints**: Must not affect redirect-path latency/behavior; must be configurable.
- **Technical context**: `GF-102`, `docs/risks/security.md` R-007.

## Definition of Done

- [ ] Code implemented per acceptance criteria
- [ ] Tests written and passing
- [ ] Quality gates passed
- [ ] AI Work Log entry closed
- [ ] `docs/risks/security.md` R-007 marked Mitigated

## Dev Notes

*(fill in once complete)*

## Related

- Risk: R-007 (docs/risks/security.md)
