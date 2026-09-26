# [GF-102] `POST /api/v1/urls` — create short URL

| Field | Value |
|---|---|
| **Type** | Story |
| **Module** | Core URL Management |
| **Epic / Phase** | Phase 1 — Greenfield Core |
| **Status** | Not Started |
| **Priority** | High |
| **Depends on** | GF-101 |
| **Blocks** | GF-106, GF-107, GF-108 |
| **AI Work Log** | docs/ai-work-log/entries/GF-102.md (open when work starts) |

## Summary

Implement the endpoint that accepts a long URL and returns a generated short code.

## Description

Per `docs/decisions/0003-id-generation-strategy.md`, use a random Base62 generator with a
bounded retry loop on collision (interim, naive implementation — refactor is planned in
`BF-202`). This is a **high-impact task** per `docs/conventions/ai-usage-rules.md` (ID
generation strategy is on the high-impact list) — requires explicit Reviewer Decision before merge.

## Acceptance Criteria

- [ ] `POST /api/v1/urls` accepts `{ "originalUrl": "..." }`, returns `201` with the created
      short code + full short URL
- [ ] Basic URL syntax validation (malformed URLs rejected with `400`); full scheme/target
      validation deferred to `VAL-403`
- [ ] Collision retry is bounded (max attempts), not unbounded (this is the known interim risk —
      see R-002); exceeding the bound returns a `503`-class error, not a hang
- [ ] Response DTO does not expose internal entity fields beyond what's needed

## Technical Notes / Constraints

- Do not implement full open-redirect protection here — that's `VAL-403`'s job. Keep this task
  scoped to "does a valid-looking URL get a working short code."
- Do not implement rate limiting here — that's `VAL-402`.

## AI Collaboration Plan

- **Intent**: Implement the create endpoint with the interim naive generator.
- **Constraints**: Bounded retry only; no rate limiting or full redirect-target validation in
  this task (those are separate tasks — don't scope-creep); must go through `dto/` for request/response.
- **Acceptance criteria**: as listed above.
- **Technical context**: `docs/decisions/0003-id-generation-strategy.md`,
  `docs/architecture/03-api-design.md`, `GF-101` entity/repository.

## Definition of Done

- [ ] Code implemented per acceptance criteria
- [ ] Tests written and passing (including a collision-retry-bound test)
- [ ] Quality gates passed
- [ ] AI Work Log entry closed **with Reviewer Decision: APPROVED** (high-impact: ID generation)
- [ ] `docs/architecture/01-component-architecture.md` status table updated

## Dev Notes

*(fill in once complete)*

## Related

- Decision: 0003-id-generation-strategy.md
- Risk: R-001, R-002 (docs/risks/data-integrity.md)
