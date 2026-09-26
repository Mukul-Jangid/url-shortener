# [GF-102] `POST /api/v1/urls` — create short URL

| Field | Value |
|---|---|
| **Type** | Story |
| **Module** | Core URL Management |
| **Epic / Phase** | Phase 1 — Greenfield Core |
| **Status** | In Review |
| **Priority** | High |
| **Depends on** | GF-101, GF-106 |
| **Blocks** | GF-103, GF-107, GF-108 |
| **AI Work Log** | docs/ai-work-log/entries/GF-102.md (open when work starts) |

## Summary

Implement the endpoint that accepts a long URL and returns a generated short code.

## Description

Per `docs/decisions/0003-id-generation-strategy.md`, use a random Base62 generator with a
bounded retry loop on collision. Decision 0005 retains this as the initial approach;
`BF-201` must justify any later replacement. This is a **high-impact task** per `docs/conventions/ai-usage-rules.md` (ID
generation strategy is on the high-impact list) — requires explicit Reviewer Decision before merge.

## Acceptance Criteria

- [x] `POST /api/v1/urls` accepts `{ "originalUrl": "..." }`, returns `201` with the created
      short code + full short URL
- [x] Use GF-106 validation: nonblank absolute HTTP/HTTPS URL with a host and a documented
      length limit. Malformed/unsupported targets return `400` with `ProblemDetail`.
- [x] Collision retry is bounded (max attempts), not unbounded (this is the known interim risk —
      see R-002); exceeding the bound returns a `503`-class error, not a hang
- [x] Response DTO does not expose internal entity fields beyond what's needed

## Technical Notes / Constraints

- Before implementation, record exact response fields, code length, retry limit, and configured
  public base URL in this task and the API doc. Define a local default; do not derive links
  blindly from incoming Host headers.
- Enforce basic syntax and scheme validation now. Additional destination policy is VAL-403;
  no DNS lookup or remote URL fetching in the first version.
- A unique-constraint collision must be retried in a usable transaction; an existence check
  alone is not a uniqueness guarantee. Test the collision and retry-bound failure behavior.
- Do not implement rate limiting here — that's `VAL-402`.

## AI Collaboration Plan

- **Intent**: Implement the create endpoint with the simple bounded random generator.
- **Constraints**: Bounded retry only; basic URL validation required; no rate limiting or advanced destination policy in
  this task (those are separate tasks — don't scope-creep); must go through `dto/` for request/response.
- **Acceptance criteria**: as listed above.
- **Technical context**: `docs/decisions/0003-id-generation-strategy.md`,
  `docs/architecture/03-api-design.md`, `GF-101` entity/repository.

## Definition of Done

- [x] Code implemented per acceptance criteria
- [x] Tests written and passing (valid/invalid input, database collision handling, retry bound)
- [x] README includes a verified create example
- [x] Quality gates passed
- [x] AI Work Log entry closed **with Reviewer Decision: APPROVED** (high-impact: ID generation)
- [x] `docs/architecture/01-component-architecture.md` status table updated

## Dev Notes

*(fill in once complete)*

## Related

- Decision: 0003-id-generation-strategy.md
- Risk: R-001, R-002 (docs/risks/data-integrity.md)
