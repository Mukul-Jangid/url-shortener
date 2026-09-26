# [GF-104] `GET /api/v1/urls/{code}` — metadata lookup

| Field | Value |
|---|---|
| **Type** | Story |
| **Module** | Core URL Management |
| **Epic / Phase** | Phase 1 — Greenfield Core |
| **Status** | In Progress |
| **Priority** | Medium |
| **Depends on** | GF-103 |
| **Blocks** | GF-105, GF-107, GF-108 |
| **AI Work Log** | docs/ai-work-log/entries/GF-104.md |

## Summary

Non-redirecting lookup — returns metadata about a short URL as JSON, for API consumers that need
to inspect a link without following it.

## Acceptance Criteria

- [x] `GET /api/v1/urls/{code}` returns `200` with `{ code, originalUrl, createdAt, active }`
      for an existing code (active or inactive — this endpoint doesn't filter, unlike the
      redirect endpoint)
- [x] Returns `404` for a code that has never existed
- [x] Response goes through a dedicated response DTO, not the entity directly

## Technical Notes / Constraints

- Deliberately does not include `clickCount` in the response — that belongs to the analytics
  endpoint (`AMB-303`) once its scope is settled, to keep this endpoint's contract stable
  regardless of how analytics evolves.

## AI Collaboration Plan

- **Intent**: Implement a read-only metadata endpoint.
- **Constraints**: Must use a response DTO; must not include analytics fields.
- **Acceptance criteria**: as listed above.
- **Technical context**: `GF-101`, `docs/architecture/03-api-design.md`.

## Definition of Done

- [x] Code implemented per acceptance criteria
- [x] Tests written and passing
- [x] Quality gates passed
- [x] AI Work Log entry closed

## Dev Notes

*(fill in once complete)*

## Related

- —
