# [GF-103] `GET /{code}` — redirect endpoint

| Field | Value |
|---|---|
| **Type** | Story |
| **Module** | Core URL Management |
| **Epic / Phase** | Phase 1 — Greenfield Core |
| **Status** | Not Started |
| **Priority** | High |
| **Depends on** | GF-101 |
| **Blocks** | GF-106, GF-107 |
| **AI Work Log** | docs/ai-work-log/entries/GF-103.md |

## Summary

The public redirect endpoint — the highest-traffic path in the system (see
`docs/architecture/05-nfr-and-scalability.md`).

## Description

Given a short code, look up the active `ShortUrl` and issue an HTTP redirect (302) to
`originalUrl`. Unknown or deactivated codes return `404`. No caching yet — that's `BF-203`; this
task is a straightforward DB-lookup-then-redirect, kept deliberately simple so `BF-203`'s
before/after is a clean, demonstrable brownfield diff.

## Acceptance Criteria

- [ ] `GET /{code}` returns `302` with `Location` header set to `originalUrl` for an active code
- [ ] Returns `404` for unknown or deactivated codes
- [ ] Does not increment click count in this task (analytics recording is `AMB-304`, sequenced
      later on purpose — see `docs/tasks/00-index.md` sequencing rationale)

## Technical Notes / Constraints

- Keep this implementation intentionally simple/uncached — it is the deliberate "before" state
  for the brownfield caching task.

## AI Collaboration Plan

- **Intent**: Implement the redirect lookup, no caching, no click recording yet.
- **Constraints**: Do not add caching or click-count logic in this task — those belong to later,
  separately-tracked tasks (`BF-203`, `AMB-304`).
- **Acceptance criteria**: as listed above.
- **Technical context**: `GF-101` entity/repository, `docs/architecture/03-api-design.md`.

## Definition of Done

- [ ] Code implemented per acceptance criteria
- [ ] Tests written and passing (active code, unknown code, deactivated code)
- [ ] Quality gates passed
- [ ] AI Work Log entry closed

## Dev Notes

*(fill in once complete)*

## Related

- Risk: R-005 (docs/risks/performance-scalability.md) — flagged here, mitigated later in BF-203
