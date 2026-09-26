# [BF-203] Caching layer in front of redirect lookup

| Field | Value |
|---|---|
| **Type** | Task |
| **Module** | ID Generation Hardening (Brownfield) |
| **Epic / Phase** | Phase 2 — Brownfield |
| **Status** | Not Started |
| **Priority** | High |
| **Depends on** | BF-201 |
| **Blocks** | BF-204, AMB-304, VAL-404 |
| **AI Work Log** | docs/ai-work-log/entries/BF-203.md |

## Summary

Add a cache (Spring `@Cacheable`, in-memory Caffeine for this exercise) in front of `GET
/{code}`'s lookup, since it's the highest-traffic path (R-005,
`docs/architecture/05-nfr-and-scalability.md`).

## Impact Analysis

- Modules/files affected: `service` layer for redirect lookup; `config/` (new cache config)
- Existing behavior that must not change: `GET /{code}` status codes and `Location` header
  behavior for active/unknown/deactivated codes (per BF-201 findings)
- New behavior to handle carefully: cache invalidation on `DELETE /api/v1/urls/{code}`
  (deactivation) — a stale cache entry must not keep redirecting a deactivated code

## Acceptance Criteria

- [ ] `GET /{code}` lookups are served from cache on repeat requests for the same code
- [ ] Deactivating a code (`GF-105`) invalidates its cache entry — verified by a test that
      deactivates then immediately re-requests the redirect and expects `404`
- [ ] Cache is bounded (max size / TTL) — not unbounded growth
- [ ] `docs/architecture/05-nfr-and-scalability.md` "Reliability" row updated to reflect this

## AI Collaboration Plan

- **Intent**: Add a bounded cache with correct invalidation on deactivation.
- **Constraints**: Must invalidate on deactivate; must be bounded; must not change GF-103's
  external contract.
- **Acceptance criteria**: as listed above.
- **Technical context**: BF-201's impact map, `GF-103`, `GF-105`.

## Definition of Done

- [ ] Code implemented per acceptance criteria
- [ ] Tests written and passing, including the deactivation-invalidation test
- [ ] Quality gates passed
- [ ] AI Work Log entry closed
- [ ] `docs/architecture/` updated

## Dev Notes

*(fill in once complete)*

## Related

- Risk: R-005 (docs/risks/performance-scalability.md)
