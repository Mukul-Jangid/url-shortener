# [AMB-303] `GET /api/v1/urls/{code}/analytics` endpoint

| Field | Value |
|---|---|
| **Type** | Story |
| **Module** | Analytics (Ambiguous Scenario) |
| **Epic / Phase** | Phase 3 — Ambiguous Requirement |
| **Status** | Not Started (blocked pending AMB-301/AMB-302) |
| **Priority** | Medium |
| **Depends on** | AMB-302 |
| **Blocks** | — |
| **AI Work Log** | docs/ai-work-log/entries/AMB-303.md |

## Summary

Expose the analytics data (whatever AMB-301 scoped it to be) via a read endpoint.

## Acceptance Criteria

- [ ] *(finalize against AMB-301's chosen scope before starting)*
- [ ] Returns `404` for an unknown code, consistent with GF-104's convention
- [ ] Response goes through a dedicated DTO
- [ ] `docs/architecture/03-api-design.md` endpoint inventory updated

## AI Collaboration Plan

- **Intent**: *(fill in once AMB-301/AMB-302 conclude)*
- **Constraints**: Must reuse the `404` convention already established in GF-104, not invent a
  new error shape.
- **Technical context**: AMB-301, AMB-302, `docs/conventions/error-handling.md`.

## Definition of Done

- [ ] Code implemented per finalized acceptance criteria
- [ ] Tests written and passing
- [ ] Quality gates passed
- [ ] AI Work Log entry closed
- [ ] `03-api-design.md` updated

## Dev Notes

*(fill in once complete)*

## Related

- —
